"use client"

import { useEffect } from "react"
import { useRouter } from "next/navigation"
import { getToken } from "@/lib/auth/token"

// Componente de la página del dashboard (ruta / dashboard).
export default function DashboardPage() {
  const router = useRouter()

  useEffect(() => {
    const token = getToken()
    if (!token) router.replace("/login")
  }, [router])

  return (
    <div className="p-6">
      <h1 className="text-2xl font-semibold">Dashboard</h1>
    </div>
  )
}
