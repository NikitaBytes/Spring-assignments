-- Сначала удаляем, если уже есть (в правильном порядке, с учётом FK)
DROP TABLE IF EXISTS matches;
DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS leagues;
DROP TABLE IF EXISTS coaches;

-- Затем создаём заново
CREATE TABLE coaches (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE leagues (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE teams (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       coach_id BIGINT NOT NULL,
                       league_id BIGINT NOT NULL,
                       FOREIGN KEY (coach_id) REFERENCES coaches(id),
                       FOREIGN KEY (league_id) REFERENCES leagues(id)
);

CREATE TABLE players (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         team_id BIGINT NOT NULL,
                         FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE matches (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         home_team_id BIGINT NOT NULL,
                         away_team_id BIGINT NOT NULL,
                         match_date DATETIME NOT NULL,
                         home_score INT NOT NULL,
                         away_score INT NOT NULL,
                         FOREIGN KEY (home_team_id) REFERENCES teams(id),
                         FOREIGN KEY (away_team_id) REFERENCES teams(id)
);