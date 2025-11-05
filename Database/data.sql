-- =========================================================
-- SISTEMA DE GESTIÓN INTEGRAL PARA VETERINARIA "HAPPY FEET"
-- Archivo: data.sql (Datos Iniciales y de Prueba)
-- =========================================================
--Crear rama 
-- Asegurarse de usar la base de datos correcta
USE happy_feet_veterinaria;

-- =========== DATOS INICIALES (LOOKUP TABLES) ===========

-- Especies
INSERT INTO especies (nombre, descripcion) VALUES
('Perro', 'Canis lupus familiaris'),
('Gato', 'Felis catus'),
('Ave', 'Aves domésticas'),
('Roedor', 'Pequeños mamíferos roedores'),
('Reptil', 'Reptiles domésticos'),
('Conejo', 'Oryctolagus cuniculus');

-- Razas (ejemplos básicos)
-- Asumiendo los IDs: 1=Perro, 2=Gato, 3=Ave, 4=Roedor, 5=Reptil, 6=Conejo
INSERT INTO razas (especie_id, nombre, caracteristicas) VALUES
(1, 'Labrador Retriever', 'Tamaño grande, amigable, energético'),
(1, 'Golden Retriever', 'Tamaño grande, inteligente, leal'),
(1, 'Pastor Alemán', 'Tamaño grande, inteligente, protector'),
(1, 'Bulldog Francés', 'Tamaño pequeño, cariñoso, juguetón'),
(1, 'Chihuahua', 'Tamaño muy pequeño, alerta, valiente'),
(1, 'Poodle', 'Inteligente, hipoalergénico'),
(1, 'Mestizo', 'Cruza de razas'),
(2, 'Persa', 'Pelo largo, tranquilo'),
(2, 'Siamés', 'Vocal, social, activo'),
(2, 'Maine Coon', 'Grande, peludo, amigable'),
(2, 'Bengalí', 'Activo, manchas de leopardo'),
(2, 'Mestizo', 'Cruza de razas'),
(3, 'Canario', 'Pequeño, cantante'),
(3, 'Periquito', 'Pequeño, social'),
(3, 'Loro', 'Inteligente, longevo'),
(4, 'Hámster Sirio', 'Pequeño, nocturno'),
(4, 'Cobayo', 'Mediano, social'),
(5, 'Iguana Verde', 'Herbívoro, grande'),
(5, 'Tortuga', 'Lenta, longeva'),
(6, 'Conejo Enano', 'Pequeño, dócil'),
(6, 'Conejo Gigante', 'Grande, tranquilo');

-- Tipos de productos
INSERT INTO producto_tipos (nombre, descripcion) VALUES
('Medicamento', 'Fármacos y medicinas veterinarias'),
('Vacuna', 'Vacunas y biológicos'),
('Insumo Médico', 'Material médico y quirúrgico'),
('Alimento', 'Alimento para mascotas'),
('Accesorio', 'Accesorios y productos de cuidado');

-- Tipos de eventos médicos
INSERT INTO evento_tipos (nombre, descripcion) VALUES
('Vacunación', 'Aplicación de vacunas'),
('Consulta General', 'Consulta veterinaria general'),
('Cirugía', 'Procedimiento quirúrgico'),
('Desparasitación', 'Tratamiento antiparasitario'),
('Control de Peso', 'Seguimiento de peso'),
('Examen de Sangre', 'Análisis de laboratorio'),
('Radiografía', 'Estudio de imagen'),
('Emergencia', 'Atención de emergencia'),
('Limpieza Dental', 'Profilaxis dental');

-- Estados de citas
INSERT INTO cita_estados (nombre, descripcion) VALUES
('Programada', 'Cita agendada pendiente'),
('Confirmada', 'Cita confirmada por el cliente'),
('En Proceso', 'Mascota siendo atendida'),
('Finalizada', 'Cita completada'),
('Cancelada', 'Cita cancelada'),
('No Asistió', 'Cliente no se presentó');


-- =========== DATOS DE PRUEBA ADICIONALES ===========

-- Veterinarios
INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion) VALUES
('Dr. Ricardo Sanz', '1018901234', 'VET-12345', 'Cirugía General', '3001234567', 'ricardo.sanz@happyfeet.com', '2015-01-10'),
('Dra. Laura Gómez', '1018905678', 'VET-67890', 'Medicina Felina', '3007654321', 'laura.gomez@happyfeet.com', '2018-05-20'),
('Dr. Andrés Castro', '1018909012', 'VET-10112', 'Emergencias', '3009990000', 'andres.castro@happyfeet.com', '2020-11-01');

-- Dueños
INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia) VALUES
('Sofía Hernández', '52123456', 'Calle 10 # 5-20', '3105551111', 'sofia.h@mail.com', 'Juan Hernández'), -- ID 1
('Pedro Ramírez', '1019001000', 'Av. Central 8-45', '3206662222', 'pedro.r@mail.com', 'Luisa Ramírez'), -- ID 2
('Ana Beltrán', '1017505050', 'Carrera 7 # 15-30', '3157773333', 'ana.b@mail.com', NULL); -- ID 3

-- Mascotas
-- Raza ID 7 = Mestizo (Perro), Raza ID 12 = Mestizo (Gato)
INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, microchip, alergias, condiciones_preexistentes) VALUES
(1, 'Max', 7, '2019-03-15', 'Macho', 15.50, '981000000123456', 'Polen, Fármaco X', NULL), -- ID 1
(2, 'Luna', 12, '2021-08-20', 'Hembra', 4.10, '981000000789012', NULL, 'Asma Felina'), -- ID 2
(3, 'Boby', 1, '2018-01-01', 'Macho', 32.00, NULL, NULL, 'Displasia de Cadera'), -- ID 3
(1, 'Mia', 9, '2022-04-10', 'Hembra', 3.50, '981000000456789', NULL, NULL); -- ID 4

-- Inventario (Productos)
-- Tipos: 1=Medicamento, 2=Vacuna, 3=Insumo Médico, 4=Alimento
INSERT INTO inventario (nombre_producto, producto_tipo_id, fabricante, cantidad_stock, stock_minimo, unidad_medida, fecha_vencimiento, precio_compra, precio_venta, requiere_receta) VALUES
-- Medicamentos
('Amoxicilina 250mg', 1, 'Farmavet', 150, 50, 'pastilla', '2026-12-31', 1.50, 3.50, TRUE), -- ID 1
('Analgésico Vet', 1, 'VetCorp', 80, 20, 'ml', '2025-10-01', 5.00, 12.00, TRUE), -- ID 2
-- Vacunas
('Vacuna Antirrábica', 2, 'Biovet', 60, 15, 'dosis', '2025-07-30', 10.00, 25.00, FALSE), -- ID 3
('Triple Felina', 2, 'Biovet', 45, 10, 'dosis', '2025-04-15', 15.00, 35.00, FALSE), -- ID 4
-- Insumos
('Jeringas 5ml (caja)', 3, 'MedStock', 25, 10, 'caja', '2027-01-01', 8.00, 15.00, FALSE), -- ID 5
('Gasa Estéril (paquete)', 3, 'MedStock', 200, 50, 'paquete', '2028-01-01', 0.50, 1.25, FALSE); -- ID 6

-- Servicios
INSERT INTO servicios (nombre, descripcion, categoria, precio_base, duracion_estimada_minutos) VALUES
('Consulta General Perro', 'Revisión y chequeo rutinario', 'Consulta', 45.00, 30), -- ID 1
('Consulta General Gato', 'Revisión y chequeo rutinario', 'Consulta', 48.00, 30), -- ID 2
('Esterilización Canina (Macho)', 'Cirugía de castración', 'Cirugía', 180.00, 90), -- ID 3
('Esterilización Canina (Hembra)', 'Cirugía de ovariohisterectomía', 'Cirugía', 250.00, 120), -- ID 4
('Limpieza Dental', 'Profilaxis y pulido', 'Procedimiento', 90.00, 60), -- ID 5
('Aplicación de Vacuna', 'Servicio de administración de biológico', 'Vacunación', 5.00, 15); -- ID 6

-- Citas (Programadas y Finalizadas)
-- Estados: 1=Programada, 4=Finalizada
INSERT INTO citas (mascota_id, veterinario_id, fecha_hora, motivo, estado_id) VALUES
(1, 1, '2025-11-05 10:00:00', 'Revisión anual y vacunas', 1),
(2, 2, '2025-10-25 14:30:00', 'Tos persistente', 4), -- Finalizada
(3, 3, '2025-11-05 11:30:00', 'Control de cadera', 1);

-- Consultas Médicas (Resultado de la cita ID 2)
-- Asumiendo cita_id 2 finalizada.
INSERT INTO consultas_medicas (mascota_id, veterinario_id, cita_id, fecha_hora, motivo, sintomas, diagnostico, recomendaciones, peso_registrado) VALUES
(2, 2, 2, '2025-10-25 14:30:00', 'Tos persistente', 'Tos seca intermitente, dificultad para respirar leve', 'Bronquitis felina', 'Medicación oral y humidificador', 4.10); -- ID 1

-- Prescripciones (Basada en la Consulta ID 1)
-- Producto ID 2: Analgésico Vet
INSERT INTO prescripciones (consulta_id, producto_id, cantidad, dosis, frecuencia, duracion_dias, instrucciones) VALUES
(1, 2, 10, '0.5 ml', 'Cada 12 horas', 5, 'Administrar con la comida. Si no mejora, volver en 7 días.');

-- Movimientos de Inventario (Salida por prescripción)
-- El sistema de Java debería generar estos movimientos automáticamente, pero se añade una simulación:
INSERT INTO movimientos_inventario (producto_id, tipo_movimiento, cantidad, stock_anterior, stock_nuevo, motivo, referencia_consulta_id, usuario) VALUES
(2, 'Salida', 10, 80, 70, 'Prescripción médica', 1, 'Dra. Laura Gómez');

-- Facturas (Basada en la Consulta ID 1, Dueño ID 2)
INSERT INTO facturas (dueno_id, numero_factura, fecha_emision, subtotal, impuesto, descuento, total, metodo_pago, estado) VALUES
(2, 'F-202510-0001', '2025-10-25 15:00:00', 57.00, 5.70, 0.00, 62.70, 'Tarjeta', 'Pagada'); -- ID 1

-- Items de Factura (Basado en Factura ID 1)
-- Item 1: Servicio ID 2 (Consulta Gato: 48.00)
-- Item 2: Producto ID 2 (Analgésico: 12.00 x 10 unidades = 120.00 -> ERROR EN CÁLCULO DE VENTA. Se asume que el precio de venta es por la unidad dispensada, no el total recetado.)
-- ASUMIENDO que el Analgésico fue facturado con un costo total de 9.00 por la dosis total.
INSERT INTO items_factura (factura_id, tipo_item, producto_id, servicio_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(1, 'Servicio', NULL, 2, 'Consulta General Gato', 1, 48.00, 48.00),
(1, 'Producto', 2, NULL, 'Analgésico Vet (Dosis Total)', 1, 9.00, 9.00); -- Total Subtotal: 57.00