// APIs related to user_routes.py in the server

import { fetchWithRefresh } from "./AuthRoutes";

export const getUser = async (API_URL) => {
  try {
    const res = await fetchWithRefresh(`${API_URL}/user/get`, API_URL, {
      method: "GET",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (res.ok) {
      let data = null;
      try {
        data = await res.json();
      } catch (err) {
        console.warn("Could not parse response JSON:", err);
      }
      return { ok: res.ok, data };
    }
    return { ok: res.ok };
  } catch (error) {
    console.error("Error getting user:", error);
    return { ok: false };
  }
};

export const deleteUser = async (API_URL) => {
  try {
    const res = await fetchWithRefresh(`${API_URL}/user/delete`, API_URL, {
      method: "DELETE",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    });
    return { ok: res.ok };
  } catch (error) {
    return { ok: false };
  }
};
