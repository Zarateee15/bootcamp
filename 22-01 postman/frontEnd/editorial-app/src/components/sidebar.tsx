"use client"

import Link from "next/link"
import { usePathname, useRouter } from "next/navigation"
import { Button } from "@/components/ui/button"
import { cn } from "@/lib/utils"
import { clearToken } from "@/lib/auth/token"

const items = [
  { href: "/dashboard", label: "Dashboard" },
  { href: "/prestamos", label: "Préstamos" },
]

export function Sidebar() {
  const pathname = usePathname()
  const router = useRouter()

  const logout = () => {
    clearToken()
    router.push("/login")
  }

  return (
    <aside className="w-64 border-r bg-background">
      <div className="p-4 border-b">
        <p className="font-semibold">Editorial App</p>
        <p className="text-sm text-muted-foreground">Panel</p>
      </div>

      <nav className="p-2 space-y-1">
        {items.map((it) => {
          const active = pathname === it.href
          return (
            <Button
              key={it.href}
              asChild
              variant={active ? "secondary" : "ghost"}
              className={cn("w-full justify-start")}
            >
              <Link href={it.href}>{it.label}</Link>
            </Button>
          )
        })}
      </nav>

      <div className="mt-auto p-4 border-t">
        <Button variant="outline" className="w-full" onClick={logout}>
          Salir
        </Button>
      </div>
    </aside>
  )
}
