"use client"

import { useQuery } from "@tanstack/react-query"
import { getPrestamos } from "../../../lib/api/prestamos"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"

export default function PrestamosPage() {
  const { data, isLoading, error } = useQuery({
    queryKey: ["prestamos"],
    queryFn: getPrestamos,
  })

  return (
    <Card>
      <CardHeader>
        <CardTitle>Préstamos registrados</CardTitle>
      </CardHeader>

      <CardContent>
        {isLoading && <p>Cargando préstamos...</p>}

        {error instanceof Error && (
          <p className="text-sm text-red-600">Error: {error.message}</p>
        )}

        {!isLoading && !error && (!data || data.length === 0) && (
          <p className="text-muted-foreground">No hay préstamos registrados.</p>
        )}

        {!isLoading && !error && data && data.length > 0 && (
          <div className="border rounded-md overflow-hidden">
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>ID</TableHead>
                  <TableHead>Fecha</TableHead>
                  <TableHead>Usuario</TableHead>
                  <TableHead>Libro</TableHead>
                </TableRow>
              </TableHeader>

              <TableBody>
                {data.map((p) => (
                  <TableRow key={p.id}>
                    <TableCell>{p.id}</TableCell>
                    <TableCell>{p.fecha ?? "-"}</TableCell>
                    <TableCell>{p.usuario ?? "-"}</TableCell>
                    <TableCell>{p.libro ?? "-"}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        )}
      </CardContent>
    </Card>
  )
}
