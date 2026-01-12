

-- 1) Top clientes con más facturas
SELECT 	C.id,
		C.nombre, 
		C.apellido,
		COUNT(*) AS "Total Facturas" 
FROM FACTURA F
JOIN CLIENTE C ON C.id = F.cliente_id
GROUP BY C.id, C.nombre, C.apellido
ORDER BY 4 DESC
LIMIT 10;


-- 2) Top clientes que más gastaron
SELECT	C.id AS "ID",
		C.nombre AS "Nombre", 
		C.apellido AS "Apellido",
		trunc(SUM(FD.cantidad * PR.precio)) AS "Total Gastado"
FROM CLIENTE C
JOIN FACTURA F ON C.id = F.cliente_id 
JOIN FACTURA_DETALLE FD ON F.ID = FD.factura_id
JOIN PRODUCTO PR ON PR.id = FD.producto_id
GROUP BY C.id, C.nombre, C.apellido
ORDER BY 4 DESC
LIMIT 10;


-- 3) Top monedas más utilizadas
SELECT 	M.nombre AS "Nombre",
		COUNT(M.id) AS "Cantidad de Apariciones" 
FROM FACTURA F
JOIN MONEDA M ON M.id = F.moneda_id
GROUP BY M.nombre
ORDER BY 2 DESC
LIMIT 10;


-- 4) Top proveedor de productos 
SELECT 	PV.ID,
		PV.nombre,
		COUNT(PV.id) AS "Cantidad de Apariciones"
FROM FACTURA_DETALLE FD
JOIN PRODUCTO PRO ON PRO.id = FD.producto_id
JOIN PROVEEDOR PV ON PV.ID = PRO.proveedor_id
GROUP BY PV.id, PV.nombre
ORDER BY 3 DESC;

-- 5) Productos más vendidos
SELECT 	PR.id,
		PR.nombre,
		SUM(FD.cantidad) AS "Cantidad Vendida"
FROM FACTURA_DETALLE FD
JOIN PRODUCTO PR ON PR.id = FD.producto_id
GROUP BY PR.id
ORDER BY 3 DESC;


-- 6) Productos menos vendidos
SELECT 	PR.id,
		PR.nombre,
		SUM(FD.cantidad)
FROM FACTURA_DETALLE FD
JOIN PRODUCTO PR ON PR.id = FD.producto_id
GROUP BY PR.id
ORDER BY 3 ASC;


-- 7) Consulta que muestre fecha de emisión de factura, nombre y apellido del cliente, 
--    nombres de productos de esa factura, cantidades compradas, nombre de tipo de factura de una factura específica
SELECT	F.id AS "ID Factura",
		F.fecha_emision,
		C.nombre,
		C.apellido,
		PR.nombre,
		SUM(FD.cantidad) AS "Cantidad Comprada",
		FT.nombre
FROM FACTURA F
JOIN CLIENTE C ON C.id = F.cliente_id
JOIN FACTURA_DETALLE FD ON FD.factura_id = F.id
JOIN PRODUCTO PR ON PR.id = FD.producto_id
JOIN FACTURA_TIPO FT ON FT.id = F.factura_tipo_id
GROUP BY F.id, C.nombre, C.apellido, F.fecha_emision, PR.nombre, FT.nombre
ORDER BY 6 DESC;


-- 8) Montos de facturas ordenadas según totales
SELECT	F.id AS "ID Factura",
		F.fecha_emision AS "Fecha Emision",
		C.nombre AS "Nombre",
		C.apellido AS "Apellido",
		SUM(FD.cantidad * PR.precio) AS "Monto Total"
FROM FACTURA F
JOIN CLIENTE C ON C.id = F.cliente_id
JOIN FACTURA_DETALLE FD ON FD.factura_id = F.id
JOIN PRODUCTO PR ON PR.id = FD.producto_id
GROUP BY F.id, C.nombre, C.apellido, F.fecha_emision
ORDER BY 5 DESC;


-- 9) Mostrar el iva 10% de los montos totales de facturas (suponer que todos los productos tienen IVA 10%)
SELECT	F.id AS "ID Factura",
		F.fecha_emision AS "Fecha Emision",
		C.nombre AS "Nombre",
		C.apellido AS "Apellido",
		CAST(SUM(FD.cantidad * PR.precio) AS INT) AS "Monto Total",
		CAST(SUM(FD.cantidad * PR.precio) * 0.1 AS INT) AS "IVA 10%"
FROM FACTURA F
JOIN CLIENTE C ON C.id = F.cliente_id
JOIN FACTURA_DETALLE FD ON FD.factura_id = F.id
JOIN PRODUCTO PR ON PR.id = FD.producto_id
GROUP BY F.id, C.nombre, C.apellido, F.fecha_emision
ORDER BY 5 DESC;




