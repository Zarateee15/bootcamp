import axios, { AxiosError } from "axios"
import { httpPublic } from "@/lib/api/http"

/* Payload que enviamos al backend para logernos.
“el contenido (datos) que le mandás al servidor en el body de la request”.

En tu caso, en el login el payload es:
{ username: "algo", password: "algo" } */ 

export type LoginPayload = {
  username: string
  password: string
}

type LoginResponse = {
  token: string
}

type ApiError = {
  message?: string
}

// Función que hace la request de login al backend.
export async function loginRequest(payload: LoginPayload): Promise<string> {
  try {
    const res = await httpPublic.post<LoginResponse>("/api/auth/login", payload)

    if (!res.data?.token) {
      throw new Error("Respuesta inválida, no vino token")
    }

    return res.data.token
  } catch (err: unknown) {
    if (axios.isAxiosError(err)) {
      const axErr = err as AxiosError<ApiError>
      const status = axErr.response?.status
      const backendMsg = axErr.response?.data?.message

      const msg =
        backendMsg ??
        (status === 401 || status === 403
          ? "Usuario o contraseña incorrectos"
          : "No se pudo conectar con el servidor")

      throw new Error(msg)
    }

    if (err instanceof Error) throw err
    throw new Error("Error al iniciar sesión")
  }
}
