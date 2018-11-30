DROP TABLE IF EXISTS receipt;

CREATE TABLE IF NOT EXISTS receipt (
  id              INT     NOT NULL PRIMARY KEY,
  name       	  VARCHAR(200) NOT NULL,
  cause           VARCHAR(200) NOT NULL,
  expiration       VARCHAR(200) NOT NULL,
);

INSERT INTO receipt VALUES (1, 'Comprovante Teste- 1', 'Esse comprovante serve como teste -1', '31/12/2018');
INSERT INTO receipt VALUES (2, 'Comprovante Teste- 2', 'Esse comprovante serve como teste - 2', '31/12/2018');
INSERT INTO receipt VALUES (3, 'Comprovante Teste- 2', 'Esse comprovante serve como teste - 3', '31/12/2018');
INSERT INTO receipt VALUES (5, 'Comprovante Teste- 2', 'Esse comprovante serve como teste - 4', '31/12/2018');
INSERT INTO receipt VALUES (6, 'Comprovante Teste- 2', 'Esse comprovante serve como teste - 5', '31/12/2018');
