-- Script para limpiar y resetear la base de datos levelup_db
-- Ejecuta este script en phpMyAdmin o MySQL Workbench

-- 1. Eliminar todos los datos de las tablas (en orden correcto para evitar problemas de foreign keys)
DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM product_reviews;
DELETE FROM product;
DELETE FROM video;
DELETE FROM blog;
DELETE FROM event;
DELETE FROM reward;
DELETE FROM users;

-- 2. Resetear los auto-incrementos (si los hay)
-- ALTER TABLE orders AUTO_INCREMENT = 1;
-- ALTER TABLE order_items AUTO_INCREMENT = 1;

-- 3. Verificar que las tablas están vacías
SELECT 'Products:', COUNT(*) FROM product;
SELECT 'Videos:', COUNT(*) FROM video;
SELECT 'Blogs:', COUNT(*) FROM blog;
SELECT 'Events:', COUNT(*) FROM event;
SELECT 'Rewards:', COUNT(*) FROM reward;
SELECT 'Users:', COUNT(*) FROM users;
SELECT 'Orders:', COUNT(*) FROM orders;

-- Después de ejecutar este script, reinicia tu aplicación Spring Boot
-- El DataSeeder se ejecutará automáticamente y llenará las tablas con datos de prueba
