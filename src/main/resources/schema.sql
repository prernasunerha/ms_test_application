DROP TABLE IF EXISTS weather;

CREATE TABLE weather (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL,
  country VARCHAR(250) DEFAULT NULL,
  apiKey VARCHAR(250) DEFAULT NULL
);