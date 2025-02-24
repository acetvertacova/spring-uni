INSERT INTO Coach (first_name, last_name) VALUES
    ('John', 'Doe'),
    ('Jane', 'Smith');

INSERT INTO League (name) VALUES
    ('Premier League'),
    ('Champions League');

INSERT INTO Team (name, coach_id, league_id) VALUES
    ('Team A', 1, 1),
    ('Team B', 2, 2);

INSERT INTO Player (first_name, last_name, team_id, goals, assists) VALUES
    ('Marcus', 'Blackwood', 1, 5, 2),
    ('Rafael', 'Morales', 1, 3, 1),
    ('Henrik', 'Larsson', 2, 5, 3),
    ('Isaac', 'Bennett', 1, 1, 5),
    ('Kai', 'Tanaka', 2, 10, 3);

INSERT INTO Match (title, date, home_team_id, away_team_id, league_id) VALUES
    ('A vs B', '2025-03-01', 1, 2, 1);

INSERT INTO Team_Match_Reference (team_id, match_id) VALUES
    (1, 1),
    (2, 1);
