ALTER TABLE medicos ADD COLUMN ativo BOOLEAN;
UPDATE medicos SET ativo = TRUE;
ALTER TABLE medicos ALTER COLUMN ativo SET NOT NULL;