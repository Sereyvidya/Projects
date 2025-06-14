import { createContext, useContext, useState, useRef, useEffect } from "react";
import { refresh } from "../../api/AuthRoutes";
import { getAllItemsFromCart } from "../../api/CartItemRoutes";
import { getAllProducts } from "../../api/ProductRoutes";
import { getUser } from "../../api/UserRoutes";
import { loadStripe } from "@stripe/stripe-js";

const UserContext = createContext();

export function UserProvider({ children }) {
  // States for rendering components
  const [showLogin, setShowLogin] = useState(false);
  const [showSignup, setShowSignup] = useState(false);
  const [showProfile, setShowProfile] = useState(false);
  const [showDeleteConfirm, setShowDeleteConfirm] = useState(false);
  const [showCart, setShowCart] = useState(false);
  const [showDeliveryAddress, setShowDeliveryAddress] = useState(false);
  const [showOrderSummary, setShowOrderSummary] = useState(false);
  const [showOrderHistory, setShowOrderHistory] = useState(false);

  // States for filter products
  const [searchQuery, setSearchQuery] = useState("");
  const categories = [
    "All",
    "Fruits",
    "Vegetables",
    "Meat",
    "Seafood",
    "Dairy",
    "Pantry",
    "Beverages",
    "Bakery",
    "Spices",
    "Vegetarian",
  ];
  const [selectedCategory, setSelectedCategory] = useState("All");

  // Other states
  const [address, setAddress] = useState({
    street: "",
    city: "",
    state: "",
    zip: "",
    lat: "",
    lng: "",
  });
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [products, setProducts] = useState([]);
  const [profile, setProfile] = useState(null);
  const [orders, setOrders] = useState([]);
  const [cartItems, setCartItems] = useState([]);

  const dropdownRef = useRef(null);
  const API_URL = "http://localhost:5000";
  const MAPBOX_ACCESS_TOKEN = process.env.NEXT_PUBLIC_MAPBOX_TOKEN;
  const STRIPE_PROMISE = loadStripe(
    process.env.NEXT_PUBLIC_STRIPE_PUBLISHABLE_KEY,
  );

  const fetchProfile = async () => {
    const { ok, data } = await getUser(API_URL);
    if (ok) {
      setProfile(data);
    } else {
      console.error("Failed to fetch profile.");
    }
  };

  const fetchCart = async () => {
    if (isLoggedIn) {
      const { ok, data } = await getAllItemsFromCart(API_URL);
      if (ok) {
        setCartItems(data);
      } else {
        console.error("Failed to get items from cart.");
      }
    }
  };

  const fetchAllProducts = async () => {
    const { ok, data } = await getAllProducts(API_URL);
    if (ok) {
      setProducts(data);
    } else {
      console.error("Failed to fetch products.");
    }
  };

  const restoreSession = async () => {
    try {
      const { ok } = await getUser(API_URL);
      setIsLoggedIn(ok);
    } catch (error) {
      console.error("Failed to restore session:", error);
      setIsLoggedIn(false);
    }
  };

  // Runs when user clicks refresh
  useEffect(() => {
    restoreSession();
  }, []);

  // Runs as soon as user is logged in
  useEffect(() => {
    fetchAllProducts();
    if (isLoggedIn) {
      fetchProfile();
      fetchCart();
    }
  }, [isLoggedIn]);

  // Runs every 5 minutes to refresh the tokens
  useEffect(() => {
    const interval = setInterval(
      async () => {
        try {
          const { ok } = await refresh(API_URL);
          if (!ok) {
            console.warn("Refresh token failed or expired.");
            setIsLoggedIn(false);
            setProfile(null);
            setCartItems([]);
          }
        } catch (err) {
          console.error("Error refreshing token:", err);
        }
      },

      5 * 60 * 1000,
    ); // every 5 minutes

    return () => clearInterval(interval);
  }, []);

  const contextValue = {
    API_URL,
    MAPBOX_ACCESS_TOKEN,
    STRIPE_PROMISE,

    dropdownRef,

    searchQuery,
    setSearchQuery,
    categories,
    selectedCategory,
    setSelectedCategory,

    showLogin,
    setShowLogin,
    showSignup,
    setShowSignup,
    showProfile,
    setShowProfile,
    showDeleteConfirm,
    setShowDeleteConfirm,
    showCart,
    setShowCart,
    showDeliveryAddress,
    setShowDeliveryAddress,
    showOrderSummary,
    setShowOrderSummary,
    showOrderHistory,
    setShowOrderHistory,

    isLoggedIn,
    setIsLoggedIn,
    products,
    cartItems,
    setCartItems,
    profile,
    setProfile,
    orders,
    setOrders,
    address,
    setAddress,

    fetchCart,
    fetchAllProducts,
  };

  return (
    <UserContext.Provider value={contextValue}>{children}</UserContext.Provider>
  );
}

export function useUserContext() {
  return useContext(UserContext);
}
