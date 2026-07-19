-- Migración: Eliminar columna per_id de tb_consentimiento y agregar con_detalles
-- Ejecutar en la base de datos persona_db

-- 1. Eliminar la restricción FK y la columna per_id
ALTER TABLE persona_db.tb_consentimiento DROP CONSTRAINT IF EXISTS fk_consentimiento_persona;
ALTER TABLE persona_db.tb_consentimiento DROP COLUMN IF EXISTS per_id;

-- 2. Agregar columna con_detalles si no existe
ALTER TABLE persona_db.tb_consentimiento ADD COLUMN IF NOT EXISTS con_detalles VARCHAR(255);
