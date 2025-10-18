# 🛍️ Tienda Online — Taller JPA Relaciones

## 📘 Descripción General

Este proyecto implementa una **aplicación backend de tienda online** utilizando **Spring Boot**, **JPA/Hibernate** y **PostgreSQL**.  
El objetivo es aplicar conceptos de **relaciones entre entidades (OneToOne, OneToMany, ManyToMany)** dentro de un contexto real, manejando clientes, direcciones, productos, categorías y pedidos.

---

## 🧑‍💻 Autor
**Juan Carlos Restrepo Penagos**  
Proyecto: *Taller JPA – Relaciones (Tienda Online)*  
Materia: *Lenguaje De Programación I*  
Institución: *Fundación Escuela Tecnológica (FET)* 

---

## ⚙️ Tecnologías Utilizadas

| Componente | Tecnología |
|-------------|-------------|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.5.x |
| Persistencia | Spring Data JPA |
| Base de Datos | PostgreSQL |
| Herramienta de Build | Gradle |
| API Test | Postman |
| IDE Recomendado | IntelliJ IDEA / Eclipse |

---

## 🧩 Estructura del Proyecto

```
src/main/java/org/example/relaciones_taller/
│
├── controller/
│   ├── ClienteController.java
│   ├── CategoriaController.java
│   ├── ProductoController.java
│   └── PedidoController.java
│
├── model/
│   ├── Cliente.java
│   ├── Direccion.java
│   ├── Categoria.java
│   ├── Producto.java
│   ├── Pedido.java
│   └── ItemPedido.java
│
├── repository/
│   ├── ClienteRepository.java
│   ├── CategoriaRepository.java
│   ├── ProductoRepository.java
│   ├── PedidoRepository.java
│   └── ItemPedidoRepository.java
│
├── service/
│   ├── ClienteService.java
│   ├── CategoriaService.java
│   ├── ProductoService.java
│   └── PedidoService.java
│
└── RelacionesTallerApplication.java
```

---

## 🧠 Relaciones Implementadas

| Relación | Descripción | Tipo |
|-----------|--------------|------|
| Cliente ↔ Dirección | Un cliente tiene una dirección única | OneToOne |
| Cliente ↔ Pedido | Un cliente puede tener varios pedidos | OneToMany |
| Pedido ↔ ItemPedido | Un pedido contiene múltiples ítems | OneToMany |
| Producto ↔ ItemPedido | Un producto puede estar en varios ítems | ManyToOne |
| Producto ↔ Categoría | Un producto puede pertenecer a varias categorías | ManyToMany |

---

## 🗄️ Configuración de Base de Datos (PostgreSQL)

1. Crear base de datos en PostgreSQL:
   ```sql
   CREATE DATABASE tienda_db;
   ```

2. Configurar credenciales en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/tienda_db
   spring.datasource.username=postgres
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

---

## 🚀 Ejecución del Proyecto

### Opción 1 — Desde IntelliJ IDEA
1. Abrir el proyecto.
2. Esperar a que Gradle descargue las dependencias.
3. Ejecutar la clase `RelacionesTallerApplication`.

### Opción 2 — Desde terminal
```bash
./gradlew bootRun
```

Servidor disponible en:  
👉 `http://localhost:8080`

---

## 🧪 Pruebas con Postman

Se recomienda importar la colección Postman incluida:  
**`TiendaOnline_RelacionesTaller.postman_collection.json`**

Flujo recomendado de pruebas:

1. **Crear Cliente**
   ```json
   POST /clientes
   {
     "nombre": "Carlos Ramírez",
     "email": "carlos@example.com",
     "direccion": {
       "calle": "Calle 45 #10-22",
       "ciudad": "Bogotá",
       "codigoPostal": "110111"
     }
   }
   ```

2. **Crear Categoría**
   ```json
   POST /categorias
   { "nombre": "Tecnología" }
   ```

3. **Crear Producto**
   ```json
   POST /productos
   { "nombre": "Laptop HP Envy 14", "precio": 4500000, "stock": 5 }
   ```

4. **Asignar Categoría al Producto**
   ```json
   POST /productos/1/categorias
   ["Tecnología"]
   ```

5. **Crear Pedido**
   ```json
   POST /pedidos/cliente/1
   [
     { "producto": { "id": 1 }, "cantidad": 2 }
   ]
   ```

6. **Cambiar Estado del Pedido**
   ```
   PUT /pedidos/1/estado?valor=PAGADO
   ```

7. **Consultar Productos y Pedidos**
   ```
   GET /productos
   GET /pedidos
   ```

---

## 🧾 Ejemplo de Resultado

**Pedido creado:**
```json
{
  "id": 1,
  "cliente": { "id": 1, "nombre": "Carlos Ramírez" },
  "fecha": "2025-10-17T12:45:32",
  "estado": "NUEVO",
  "total": 9000000,
  "items": [
    {
      "cantidad": 2,
      "precioUnitario": 4500000,
      "producto": { "id": 1, "nombre": "Laptop HP Envy 14" }
    }
  ]
}
```

---

## 🧩 Dependencias Principales (`build.gradle`)
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    runtimeOnly 'org.postgresql:postgresql'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

---

## 📷 Capturas De La Consola H2



**1.Captura Categoría:**

<img width="87" height="42" alt="categoria" src="https://github.com/user-attachments/assets/139cbb20-f0c2-4553-a5be-a8c752cfad22" />

**2.Captura Clientes**

<img width="272" height="43" alt="clientes" src="https://github.com/user-attachments/assets/46adba29-6071-405f-809d-75af0b1b79cd" />

**3.Captura Dirección**

<img width="375" height="69" alt="direccion" src="https://github.com/user-attachments/assets/edb1284f-cca3-4447-b073-241775e08902" />

**4.Captura Producto**

<img width="232" height="46" alt="producto" src="https://github.com/user-attachments/assets/b2a0fa18-5799-4095-b9cb-1cef2bf5288d" />

**5.Captura Pedidos**

<img width="372" height="41" alt="pedidos" src="https://github.com/user-attachments/assets/b73ff88d-1265-479a-8c1e-6b1fc65c01d7" />

**6.Captura Items-Pedidos**

<img width="400" height="45" alt="items-pedidos" src="https://github.com/user-attachments/assets/4ac75040-08da-4e88-a144-333e1da5aa60" />

**7.Captura Producto-Categoria**

<img width="229" height="45" alt="categoria ide" src="https://github.com/user-attachments/assets/80f95c9f-f4b2-4b68-83fd-76203e8ca6d7" />

---









