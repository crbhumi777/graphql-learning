package com.learning.graphql.service;

import com.learning.graphql.domain.Player;
import com.learning.graphql.domain.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findOne(Integer id) {
        return players.stream().filter(player -> Objects.equals(player.id(), id)).findFirst();
    }

    public Player create(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id){
        Player player = players.stream().filter(p -> p.id() == id)
                .findFirst().orElseThrow(IllegalArgumentException::new);
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team){
        Player player = new Player(id, name, team);
        Player original = players.stream().filter(p -> p.id() == id)
                .findFirst().orElseThrow(IllegalArgumentException::new);
        players.set(players.indexOf(original), player);
        return player;
    }

    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet(), "Dhoni", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Rohit", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Kohli", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "Jadeja", Team.DC));
    }
}
