package org.app.exo4.config;

import org.app.exo4.model.entity.Creature;
import org.app.exo4.model.entity.CreatureType;
import org.app.exo4.repository.CreatureRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DataGenerator {

    private CreatureRepository creatureRepository;

    public DataGenerator(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    @PostConstruct
    public void generateData() {
        if (creatureRepository.count() == 0) {
            Creature dragon1 = Creature.builder()
                    .name("Smaug")
                    .age(500)
                    .weight(2000.0)
                    .dangerous(true)
                    .type(CreatureType.DRAGON)
                    .build();

            Creature dragon2 = Creature.builder()
                    .name("Bahamut")
                    .age(800)
                    .weight(3000.0)
                    .dangerous(true)
                    .type(CreatureType.DRAGON)
                    .build();

            Creature elf1 = Creature.builder()
                    .name("Legolas")
                    .age(2931)
                    .weight(70.0)
                    .dangerous(false)
                    .type(CreatureType.ELF)
                    .build();

            Creature elf2 = Creature.builder()
                    .name("Elrond")
                    .age(6000)
                    .weight(75.0)
                    .dangerous(false)
                    .type(CreatureType.ELF)
                    .build();

            Creature dwarf1 = Creature.builder()
                    .name("Gimli")
                    .age(139)
                    .weight(90.0)
                    .dangerous(false)
                    .type(CreatureType.DWARF)
                    .build();

            Creature dwarf2 = Creature.builder()
                    .name("Thorin")
                    .age(195)
                    .weight(85.0)
                    .dangerous(false)
                    .type(CreatureType.DWARF)
                    .build();

            Creature orc1 = Creature.builder()
                    .name("Azog")
                    .age(250)
                    .weight(120.0)
                    .dangerous(true)
                    .type(CreatureType.ORC)
                    .build();

            Creature orc2 = Creature.builder()
                    .name("Bolg")
                    .age(180)
                    .weight(110.0)
                    .dangerous(true)
                    .type(CreatureType.ORC)
                    .build();

            Creature human1 = Creature.builder()
                    .name("Aragorn")
                    .age(87)
                    .weight(80.0)
                    .dangerous(false)
                    .type(CreatureType.HUMAN)
                    .build();

            Creature human2 = Creature.builder()
                    .name("Boromir")
                    .age(40)
                    .weight(85.0)
                    .dangerous(false)
                    .type(CreatureType.HUMAN)
                    .build();

            creatureRepository.saveAll(Arrays.asList(
                    dragon1, dragon2, elf1, elf2, dwarf1,
                    dwarf2, orc1, orc2, human1, human2
            ));
        }
    }
}