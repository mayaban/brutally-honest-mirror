# Brutally Honest AI Mirror

## What this is
A full-stack app where users describe their habits/excuses, and it reflects
back an honest, sharp (but not cruel) assessment of what's holding them back.
Tone calibration matters: direct and unflinching, never mean.

## Stack
- Backend: Java 21 + Spring Boot (Maven)
- Frontend: React (Vite)
- Database: PostgreSQL
- AI: Claude API for generating the reflection

## Layout
Monorepo: `backend/` (Spring Boot) and `frontend/` (React/Vite) as siblings
under this root, one git repo.

## Working mode — IMPORTANT
The user is refreshing Git/Maven/Spring Boot fundamentals after years away
from hands-on CLI work. For git, Maven, and other CLI commands:
- Explain the next command and why, then let the user type/run it themselves.
- Don't run git/mvn/npm commands on their behalf unless they explicitly ask
  you to just do it.
- Explain command output after they paste it back.
- It's fine to use read-only inspection (ls, git status, cat) yourself to
  stay oriented — just don't drive the actual setup steps.
