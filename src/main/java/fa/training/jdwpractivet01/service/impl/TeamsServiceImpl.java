package fa.training.jdwpractivet01.service.impl;

import fa.training.jdwpractivet01.entities.Teams;
import fa.training.jdwpractivet01.repository.TeamsRepository;
import fa.training.jdwpractivet01.service.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamsServiceImpl implements TeamsService {
    final private TeamsRepository teamsRepository;

    @Override
    public List<Teams> findAllTeams() {
        return teamsRepository.findAll();
    }

    @Override
    @Transactional
    public Teams saveTeams(Teams teams) {
        if (teamsRepository.count() >= 16) {
            throw new IllegalArgumentException("Số đội đã đạt giới hạn tối đa");
        }
        if (teams.getId() != null && teamsRepository.existsById(teams.getId())) {
            Teams team = teamsRepository.findByTeamName(teams.getTeamName()).orElse(teams);
            if (!team.getId().equals(teams.getId())) {
                throw new IllegalArgumentException("Team name already existed.");
            }
        }

        return teamsRepository.save(teams);
    }

    @Override
    public void deleteTeams(Teams teams) {
        teamsRepository.delete(teams);
    }

    @Override
    public Optional<Teams> findTeamById(Long id) {
        return teamsRepository.findById(id);
    }


}
