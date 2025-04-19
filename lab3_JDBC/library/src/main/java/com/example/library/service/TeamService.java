package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.dto.CoachDTO;
import com.example.library.dto.LeagueDTO;
import com.example.library.dto.MatchDTO;
import com.example.library.dto.PlayerDTO;
import com.example.library.dto.TeamCreateUpdateDTO;
import com.example.library.dto.TeamDTO;
import com.example.library.model.Coach;
import com.example.library.model.League;
import com.example.library.model.Team;
import com.example.library.repository.CoachRepository;
import com.example.library.repository.LeagueRepository;
import com.example.library.repository.MatchRepository;
import com.example.library.repository.PlayerRepository;
import com.example.library.repository.TeamRepository;

/**
 * Service layer for managing Team entities.
 * Handles business logic related to teams, including CRUD operations and data transformation.
 */
@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final LeagueRepository leagueRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    /**
     * Constructs a TeamService with necessary repositories.
     *
     * @param teamRepository   Repository for team data access.
     * @param coachRepository  Repository for coach data access.
     * @param leagueRepository Repository for league data access.
     * @param playerRepository Repository for player data access.
     * @param matchRepository  Repository for match data access.
     */
    public TeamService(TeamRepository teamRepository,
                       CoachRepository coachRepository,
                       LeagueRepository leagueRepository,
                       PlayerRepository playerRepository,
                       MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.coachRepository = coachRepository;
        this.leagueRepository = leagueRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    /**
     * Retrieves all teams and converts them to DTOs.
     *
     * @return A list of TeamDTOs.
     */
    public List<TeamDTO> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a team by its ID and converts it to a DTO.
     *
     * @param id The ID of the team to find.
     * @return The TeamDTO corresponding to the found team.
     * @throws NotFoundException if the team with the given ID is not found.
     */
    public TeamDTO findById(Long id) {
        return toDTO(findTeamOrThrow(id));
    }

    /**
     * Creates a new team based on the provided DTO.
     * Validates the existence of related entities (coach, league, players).
     *
     * @param dto The DTO containing data for the new team.
     * @return The DTO of the newly created team.
     * @throws NotFoundException if any related entity (coach, league, player) is not found.
     */
    public TeamDTO create(TeamCreateUpdateDTO dto) {
        // проверяем, что связанные сущности существуют
        coachRepository.findById(dto.getCoachId());
        leagueRepository.findById(dto.getLeagueId());
        dto.getPlayerIds().forEach(playerRepository::findById);

        Team team = new Team();
        team.setName(dto.getName());
        team.setCoachId(dto.getCoachId());
        team.setLeagueId(dto.getLeagueId());
        Long id = teamRepository.create(team);
        return findById(id);
    }

    /**
     * Updates an existing team with the data from the provided DTO.
     * Validates the existence of the team and related entities.
     *
     * @param id  The ID of the team to update.
     * @param dto The DTO containing the updated data.
     * @return The DTO of the updated team.
     * @throws NotFoundException if the team or any related entity is not found.
     */
    public TeamDTO update(Long id, TeamCreateUpdateDTO dto) {
        Team existing = findTeamOrThrow(id);
        coachRepository.findById(dto.getCoachId());
        leagueRepository.findById(dto.getLeagueId());
        dto.getPlayerIds().forEach(playerRepository::findById);

        existing.setName(dto.getName());
        existing.setCoachId(dto.getCoachId());
        existing.setLeagueId(dto.getLeagueId());
        teamRepository.update(existing);
        return findById(id);
    }

    /**
     * Deletes a team by its ID.
     *
     * @param id The ID of the team to delete.
     * @throws NotFoundException if the team with the given ID is not found.
     */
    public void delete(Long id) {
        int deleted = teamRepository.delete(id);
        if (deleted == 0) {
            throw new NotFoundException("Team not found with id " + id);
        }
    }

    /**
     * Finds a team by ID or throws a NotFoundException if not found.
     *
     * @param id The ID of the team to find.
     * @return The found Team entity.
     * @throws NotFoundException if the team is not found.
     */
    private Team findTeamOrThrow(Long id) {
        try {
            return teamRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException("Team not found with id " + id);
        }
    }

    /**
     * Converts a Team entity to a TeamDTO, fetching related entities.
     *
     * @param team The Team entity to convert.
     * @return The corresponding TeamDTO.
     */
    private TeamDTO toDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());

        // coach
        Coach coach = coachRepository.findById(team.getCoachId());
        CoachDTO cd = new CoachDTO();
        cd.setId(coach.getId());
        cd.setName(coach.getName());
        dto.setCoach(cd);

        // league
        League league = leagueRepository.findById(team.getLeagueId());
        LeagueDTO ld = new LeagueDTO();
        ld.setId(league.getId());
        ld.setName(league.getName());
        dto.setLeague(ld);

        // players
        List<PlayerDTO> players = playerRepository.findAll().stream()
            .filter(p -> p.getTeamId().equals(team.getId()))
            .map(p -> {
                PlayerDTO pd = new PlayerDTO();
                pd.setId(p.getId());
                pd.setName(p.getName());
                return pd;
            })
            .collect(Collectors.toList());
        dto.setPlayers(players);

        // matches
        List<MatchDTO> matches = matchRepository.findAll().stream()
            .filter(m -> m.getHomeTeamId().equals(team.getId())
                      || m.getAwayTeamId().equals(team.getId()))
            .map(m -> {
                MatchDTO md = new MatchDTO();
                md.setId(m.getId());
                md.setHomeTeamId(m.getHomeTeamId());
                md.setAwayTeamId(m.getAwayTeamId());
                md.setMatchDate(m.getMatchDate());
                md.setHomeScore(m.getHomeScore());
                md.setAwayScore(m.getAwayScore());
                return md;
            })
            .collect(Collectors.toList());
        dto.setMatches(matches);

        return dto;
    }
}