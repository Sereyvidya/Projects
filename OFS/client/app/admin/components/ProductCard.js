"use client";

import React from "react";
import { useAdminContext } from "../context/AdminContext";

/**
 * Renders a single product card with image, name, description, price,
 * and an "Add to Cart" or "View Cart" button.
 *
 * Props:
 * - product: object with name, image, description, price, quantity
 */
const ProductCard = ({ product }) => {
  const { setEditingProduct, setShowEditForm } = useAdminContext();
  return (
    <div className="flex flex-col items-center rounded-md bg-[#f1f0e9] p-3 text-sm shadow transition-transform duration-300 hover:scale-105 hover:shadow-lg">
      <div className="flex w-full justify-center rounded-md border-2 border-[#90b89b] bg-[#41644a] py-2">
        <h3 className="text-lg font-semibold text-[#f1f0e9] [text-shadow:_0_1px_3px_#73977b]">
          {product.name}
        </h3>
      </div>
      <div className="relative mt-3 w-full overflow-hidden rounded-md pt-[100%]">
        <img
          src={product.image}
          alt={product.name}
          className="absolute inset-0 h-full w-full object-cover object-center"
        />
      </div>
      <div className="mt-3 flex flex-col items-center">
        <p className="text-[#41644a]">
          ${product.price}
          &nbsp;
          {product.description}
        </p>
      </div>
      <button
        className={`mt-3 rounded-full px-3 py-1.5 text-sm font-medium whitespace-nowrap shadow transition ${
          product.quantity === 0
            ? "border-2 border-red-300 bg-red-600 text-[#f1f0e9] hover:scale-105 hover:bg-red-400 focus:ring-2 focus:ring-red-500 focus:outline-none"
            : product.quantity <= 10
              ? "border-2 border-orange-300 bg-[#e9762b] text-[#f1f0e9] hover:scale-105 hover:bg-orange-400 focus:ring-2 focus:ring-orange-500 focus:outline-none"
              : "border-2 border-green-300 bg-green-600 text-[#f1f0e9] hover:scale-105 hover:bg-green-400 focus:ring-2 focus:ring-green-500 focus:outline-none"
        } `}
        onClick={() => {
          setEditingProduct(product);
          console.log(product);
          setShowEditForm(true);
        }}
      >
        {product.quantity === 0
          ? "Out of Stock"
          : product.quantity <= 10
            ? "Low on Stock"
            : "Edit"}
      </button>
    </div>
  );
};

export default ProductCard;
