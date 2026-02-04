This is a [Next.js](https://nextjs.org) project bootstrapped with [`create-next-app`](https://nextjs.org/docs/app/api-reference/cli/create-next-app).

## Getting Started

First, run the development server:

``` bash
npm run dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start editing the page by modifying `app/page.tsx`. The page auto-updates as you edit the file.

This project uses [`next/font`](https://nextjs.org/docs/app/building-your-application/optimizing/fonts) to automatically optimize and load [Geist](https://vercel.com/font), a new font family for Vercel.

## Learn More

To learn more about Next.js, take a look at the following resources:

- [Next.js Documentation](https://nextjs.org/docs) - learn about Next.js features and API.
- [Learn Next.js](https://nextjs.org/learn) - an interactive Next.js tutorial.

You can check out [the Next.js GitHub repository](https://github.com/vercel/next.js) - your feedback and contributions are welcome!

## Deploy on Vercel

The easiest way to deploy your Next.js app is to use the [Vercel Platform](https://vercel.com/new?utm_medium=default-template&filter=next.js&utm_source=create-next-app&utm_campaign=create-next-app-readme) from the creators of Next.js.

Check out our [Next.js deployment documentation](https://nextjs.org/docs/app/building-your-application/deploying) for more details.


## Estructura del proyecto (resumen)

### Carpetas
- `.next/` → salida/cache generado por Next al correr o build (no se edita).
- `node_modules/` → dependencias instaladas (no se edita).
- `public/` → archivos estáticos (imágenes, íconos, etc.).

- `src/app/` → donde Next arma las URLs de la app. Cada carpeta = un segmento de URL.
  - `(protected)/` → Esto es un Route Group. Es privada, solo entra quien esté logueado.
      - `dashboard/` → 
          - `page.tsx` → pagina principala al loggearse.
      - `layout.tsx` →  layout que envuelve todas las rutas dentro de (protected).

  - `login/` → las paginas del login. Es publica, cualquiera puede entrar.
      - `page.tsx` → es la pagina del login. 

  - `page.tsx` → ruta `/`, la raíz del sitio, lleva a /login.
  - `layout.tsx` → layout global (header/sidebar/providers). Envuelve a TODAS las páginas: /login, /dashboard, etc.
  - `globals.css` → Es tu CSS global. Tailwind base, variables de shadcn, estilos globales (body, etc.).

- `src/lib/` → (library): convención muy usada para guardar código reutilizable que no es UI (componentes visuales). Es como la “caja de herramientas” del proyecto. 
  - `api/` → se encarga de hablar con el backend (Spring): loginRequest(), getLibros(), createLibro(), etc.
      - `auth.ts` → define tipos como LoginRequest, LoginResponse
      - `entidades.ts` → CRUD de las entidades: libros, editoriales, colegios, profesores, etc.
      - `usuarios.ts` → endpoints de usuarios (admin).
      - `http.ts` → un “fetch con baseURL” y headers comunes.

  - `auth/` → Se encarga de todo lo de autenticación en el front.
      - `token.ts` → guarda (localStorage), lee y borra (logout) el token JWT.

  - `schemas/` → Validaciones con ZOD + tipos derivados.
      - `auth.ts` → define loginSchema (reglas) y LoginValues (tipo TS generado desde el schema).
    
- `src/components/` → componentes UI (botones, forms, cards, etc...).
  - `/ui` → componentes ShadCN (botones, inputs, etc...).
  - `login-form.tsx` → UI de un login.


### Archivos de config
- `package.json` → scripts y dependencias.
- `package-lock.json` → versiones exactas (generado automático).
- `next.config.ts` → configuración de Next (rara vez se toca al inicio).
- `tsconfig.json` → configuración de TypeScript.
- `eslint.config.mjs` → reglas de lint.
- `postcss.config.mjs` → configuración de PostCSS/Tailwind.
- `.gitignore` → archivos/carpetas que no se suben a git.

## Modificar (stack)
- **Rutas/páginas:** `src/app/...` (ej. `/login`, `/dashboard`, `/libros`)
- **UI (shadcn):** `src/components/ui/...`
- **Validación (Zod):** `src/lib/schemas/...` (o similar)
- **Cliente API + token:** `src/lib/api/...` (o `src/services/...`)
- **TanStack Query:** Provider global en `layout.tsx` y hooks/queries en `src/lib/queries/...`