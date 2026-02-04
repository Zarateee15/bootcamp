import axios from "axios"
import { getToken, clearToken } from "@/lib/auth/token"

// URL base del backend
const BASE_URL = "http://localhost:8080"

// Cliente HTTP sin autenticación (para login, registro, etc.)
export const httpPublic = axios.create({
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
  timeout: 10000,
})

// Cliente HTTP con autenticación (para el resto de la app)
export const http = axios.create({
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
  timeout: 10000,
})

// Agrega el token JWT a las solicitudes si está disponible
http.interceptors.request.use((config) => {
  if (typeof window !== "undefined") {
    const token = getToken()
    if (token) {
      config.headers = config.headers ?? {}
      config.headers.Authorization = `Bearer ${token}`
    }
  }
  return config
})

// Maneja errores de autorización globalmente
http.interceptors.response.use(
  (res) => res,
  (error) => {
    const status = error?.response?.status
    if (status === 401 || status === 403) {
      clearToken()
    }
    return Promise.reject(error)
  }
)
