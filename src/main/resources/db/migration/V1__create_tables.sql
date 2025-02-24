CREATE TABLE IF NOT EXISTS League (
    id serial primary key,
    name varchar(50)
);

CREATE TABLE IF NOT EXISTS Coach (
    id serial primary key,
    first_name varchar(50),
    last_name varchar(50)
);

CREATE TABLE IF NOT EXISTS Team (
    id serial primary key,
    name varchar(50),
    coach_id int unique references Coach (id),
    league_id int references League (id)
);

CREATE TABLE IF NOT EXISTS Player (
    id serial primary key,
    first_name varchar(50),
    last_name varchar(50),
    goals INT DEFAULT 0,
    assists INT DEFAULT 0,
    team_id int references Team (id)

);

CREATE TABLE IF NOT EXISTS Match (
    id serial primary key,
    title varchar(50),
    date date,
    home_team_id int references Team (id),
    away_team_id int references Team (id),
    league_id int references League (id)
);

CREATE TABLE IF NOT EXISTS Team_Match_Reference (
    team_id int references Team (id),
    match_id int references Match (id),
    primary key (team_id, match_id)
);







