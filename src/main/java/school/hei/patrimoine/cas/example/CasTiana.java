package school.hei.patrimoine.cas.example;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;

import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;
import school.hei.patrimoine.cas.Cas;

public class CasTiana extends Cas {
    private final Compte compteBancaire;
    private final Materiel terrain;
    private final FluxArgent depensesMensuelles;
    private final FluxArgent projetDepenses;
    private final FluxArgent remboursementPret;
    private final FluxArgent projetRevenus;

    public CasTiana() {
        super(LocalDate.of(2025, APRIL, 8), LocalDate.of(2026, MARCH, 31), new Personne("Tiana"));

        compteBancaire = new Compte("Compte bancaire", LocalDate.MIN, ariary(60_000_000));
        terrain = new Materiel("Terrain bâti", LocalDate.of(2025, APRIL, 8), LocalDate.of(2026, MARCH, 31), ariary(100_000_000), 0.10);
        depensesMensuelles = new FluxArgent(
                "Dépenses familiales",
                compteBancaire,
                LocalDate.of(2025, APRIL, 1),
                LocalDate.of(2026, MARCH, 1),
                12,
                ariary(-4_000_000));

        projetDepenses = new FluxArgent(
                "Dépenses du projet entrepreneurial",
                compteBancaire,
                LocalDate.of(2025, JUNE, 1),
                LocalDate.of(2025, DECEMBER, 31),
                6,
                ariary(-5_000_000));

        projetRevenus = new FluxArgent(
                "Revenus du projet entrepreneurial",
                compteBancaire,
                LocalDate.of(2025, MAY, 1),
                LocalDate.of(2026, JANUARY, 31),
                2,
                ariary(70_000_000));

        remboursementPret = new FluxArgent(
                "Remboursement du prêt bancaire",
                compteBancaire,
                LocalDate.of(2025, AUGUST, 27),
                LocalDate.of(2026, AUGUST, 27),
                12,
                ariary(-2_000_000));
    }

    @Override
    protected String nom() {
        return "Tiana";
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected void init() {
    }

    @Override
    public Set<Possession> possessions() {
        return Set.of(
                compteBancaire,
                terrain,
                depensesMensuelles,
                projetDepenses,
                projetRevenus,
                remboursementPret
        );
    }

    @Override
    protected void suivi() {
    }
}