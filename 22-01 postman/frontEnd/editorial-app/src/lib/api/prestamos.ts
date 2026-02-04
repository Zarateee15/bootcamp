import { http } from "@/lib/api/http"

export type Prestamo = {
  id: number
  fecha?: string
  usuario?: string
  libro?: string
}

export async function getPrestamos(): Promise<Prestamo[]> {
  const res = await http.get<Prestamo[]>("/api/prestamos")
  return res.data
}
