package school.hei.patrimoine.cas.example;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

public class CasBako extends Cas {
    private final Compte bni;
    private final Compte bmoi;
    private final Compte coffre;

    public CasBako() {
        super(LocalDate.of(2025, DECEMBER, 31), LocalDate.MAX, new Personne("Bako"));

        bni = new Compte("BNI - Compte courant", LocalDate.MIN, ariary(2_000_000));
        bmoi = new Compte("BMOI - Épargne", LocalDate.MIN, ariary(625_000));
        coffre = new Compte("Coffre à la maison", LocalDate.MIN, ariary(1_750_000));
    }

    @Override
    protected String nom() {
        return "Bako";
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
        var aujourdHui = LocalDate.of(2025, APRIL, 8);

        var salaire = new FluxArgent(
                "Salaire mensuel",
                bni,
                LocalDate.of(2025, APRIL, 2),
                LocalDate.of(2025, DECEMBER, 2),
                9,
                ariary(2_125_000));

        var virementSortant = new FluxArgent(
                "Virement sortant vers BMOI",
                bni,
                LocalDate.of(2025, APRIL, 3),
                LocalDate.of(2025, DECEMBER, 3),
                9,
                ariary(-200_000));

        var virementEntrant = new FluxArgent(
                "Virement reçu depuis BNI",
                bmoi,
                LocalDate.of(2025, APRIL, 3),
                LocalDate.of(2025, DECEMBER, 3),
                9,
                ariary(200_000));

        var loyer = new FluxArgent(
                "Loyer colocation",
                bni,
                LocalDate.of(2025, APRIL, 26),
                LocalDate.of(2025, DECEMBER, 26),
                9,
                ariary(-600_000));

        var depensesVie = new FluxArgent(
                "Dépenses de vie",
                bni,
                LocalDate.of(2025, APRIL, 1),
                LocalDate.of(2025, DECEMBER, 1),
                9,
                ariary(-700_000));

        var ordinateur = new Materiel(
                "Ordinateur portable",
                aujourdHui,
                aujourdHui,
                ariary(3_000_000),
                -0.12);

        return Set.of(
                bni, bmoi, coffre,
                salaire, virementSortant, virementEntrant,
                loyer, depensesVie,
                ordinateur
        );
    }

    @Override
    protected void suivi() {
    }
}
