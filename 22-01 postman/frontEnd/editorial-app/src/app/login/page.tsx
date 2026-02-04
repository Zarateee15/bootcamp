"use client" // Este componente es Client Component (corre en el navegador).

import { useState } from "react"
import { useRouter } from "next/navigation"
import { useForm } from "react-hook-form"
import { zodResolver } from "@hookform/resolvers/zod"
import { useMutation } from "@tanstack/react-query"


/* Importamos el esquema de validación y el tipo de datos para los valores del formulario
  desde el archivo de esquemas (lib/schemas/auth.ts). */
import { loginSchema, type LoginValues } from "@/lib/schemas/auth"
import { loginRequest } from "@/lib/api/auth"
import { setToken } from "@/lib/auth/token"

// Importamos algunos componentes UI desde nuestra carpeta de componentes.
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"

// Esto define el componente de React que Next muestra cuando entrás a la ruta /login.
export default function LoginPage() {
  const router = useRouter()
  
  // Esto guarda un error general del formulario (no de un campo específico). 
  // “Usuario o contraseña incorrectos” “No se pudo conectar al servidor” “Token inválido"
  const [formError, setFormError] = useState<string | null>(null)

  // Configuración de react-hook-form con zod para validación.
  const {
    register,
    handleSubmit,
    formState: { errors},
  } = useForm<LoginValues>({
    resolver: zodResolver(loginSchema),
    defaultValues: { username: "", password: "" },
  })

  // Mutación para el login usando react-query.
  const loginMutation = useMutation<string, Error, LoginValues>({
    mutationFn: loginRequest, 
    onSuccess: (token) => {
      setToken(token)
      router.push("/dashboard")
    },
    onError: (err) => {
      const msg = err.message || "Error al iniciar sesión"
      setFormError(msg)
    },
  })

  // Función que se ejecuta al enviar el formulario.
  const onSubmit = (values: LoginValues) => {
    setFormError(null)
    loginMutation.mutate(values)
  }

  // Estructura del formulario de login.
  return ( 
    <div className=" min-h-screen flex items-center justify-center p-4">
      <Card className="w-full max-w-sm">
        <CardHeader>
          <CardTitle>Prestamo de libros</CardTitle>
          <CardDescription>Ingrese sesión</CardDescription>
        </CardHeader>

        <CardContent>
          <form className="space-y-4" onSubmit={handleSubmit(onSubmit)}>
            <div className="space-y-2">
              <Label htmlFor="username">Usuario</Label>
              <Input
                id="username"
                placeholder="Tu usuario"
                autoComplete="username"
                {...register("username")}
              />
              {errors.username && (
                <p className="text-sm text-red-600">{errors.username.message}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="password">Contraseña</Label>
              <Input
                id="password"
                type="password"
                placeholder="Tu contraseña"
                autoComplete="current-password"
                {...register("password")}
              />
              {errors.password && (
                <p className="text-sm text-red-600">{errors.password.message}</p>
              )}
            </div>

            {(formError || loginMutation.error) && (
              <p className="text-sm text-red-600">
                {formError ?? loginMutation.error?.message}
              </p>
            )}

            <Button
              type="submit"
              className="w-full"
              disabled={loginMutation.isPending}
            >
              {loginMutation.isPending ? "Ingresando..." : "Entrar"}
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  )
}
