-- Script para reconstruir tablas de consentimiento en persona_db
-- La tabla de usuarios está en otro microservicio (no hay FK real)
-- Ejecutar en la base de datos persona_db

-- 1. Eliminar tablas existentes con sus restricciones dependientes
DROP TABLE IF EXISTS persona_db.tb_usuario_consentimiento CASCADE;
DROP TABLE IF EXISTS persona_db.tb_consentimiento CASCADE;

-- 2. Crear tabla tb_consentimiento (sin relación con persona)
CREATE TABLE persona_db.tb_consentimiento (
    con_id BIGSERIAL PRIMARY KEY,
    con_version_documento VARCHAR(100) NOT NULL UNIQUE,
    con_detalles VARCHAR(255)
);

-- 3. Crear tabla tb_usuario_consentimiento
-- usu_id es solo una referencia lógica al usuario (FK en otro microservicio)
CREATE TABLE persona_db.tb_usuario_consentimiento (
    usu_id BIGINT NOT NULL,
    con_id BIGINT NOT NULL,
    usu_con_fecha_aceptacion DATE NOT NULL,
    
    PRIMARY KEY (usu_id, con_id),
    
    CONSTRAINT fk_usuario_consentimiento_consentimiento
        FOREIGN KEY (con_id)
        REFERENCES persona_db.tb_consentimiento (con_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- 4. Índices adicionales para rendimiento
CREATE INDEX idx_usuario_consentimiento_con_id 
    ON persona_db.tb_usuario_consentimiento (con_id);

CREATE INDEX idx_usuario_consentimiento_fecha 
    ON persona_db.tb_usuario_consentimiento (usu_con_fecha_aceptacion);

CREATE INDEX idx_usuario_consentimiento_usu_id 
    ON persona_db.tb_usuario_consentimiento (usu_id);

-- Comentarios para documentación
COMMENT ON TABLE persona_db.tb_consentimiento IS 'Catálogo de versiones de documentos de consentimiento';
COMMENT ON COLUMN persona_db.tb_consentimiento.con_id IS 'Identificador único del consentimiento';
COMMENT ON COLUMN persona_db.tb_consentimiento.con_version_documento IS 'Versión del documento de consentimiento';
COMMENT ON COLUMN persona_db.tb_consentimiento.con_detalles IS 'Detalles o descripción del consentimiento';

COMMENT ON TABLE persona_db.tb_usuario_consentimiento IS 'Registro de aceptaciones de consentimiento por usuario';
COMMENT ON COLUMN persona_db.tb_usuario_consentimiento.usu_id IS 'Referencia al ID de usuario (gestión en microservicio externo)';
COMMENT ON COLUMN persona_db.tb_usuario_consentimiento.con_id IS 'FK a tb_consentimiento';
COMMENT ON COLUMN persona_db.tb_usuario_consentimiento.usu_con_fecha_aceptacion IS 'Fecha en que el usuario aceptó el consentimiento';
