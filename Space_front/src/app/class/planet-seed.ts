export class PlanetSeed {
  constructor(
    private _id: number | null,
    private _population: number,
    private _arme: number,
    private _mineraiRestant: number,
    private _idJoueur: number | null,
    private _idPlanete: number,
    private _idBatiments: number[]
  ) {}

  get id(): number | null {
    return this._id;
  }

  set id(value: number | null) {
    this._id = value;
  }

  get population(): number {
    return this._population;
  }

  set population(value: number) {
    this._population = value;
  }

  get arme(): number {
    return this._arme;
  }

  set arme(value: number) {
    this._arme = value;
  }

  get mineraiRestant(): number {
    return this._mineraiRestant;
  }

  set mineraiRestant(value: number) {
    this._mineraiRestant = value;
  }

  get idJoueur(): number | null {
    return this._idJoueur;
  }

  set idJoueur(value: number | null) {
    this._idJoueur = value;
  }

  get idPlanete(): number {
    return this._idPlanete;
  }

  set idPlanete(value: number) {
    this._idPlanete = value;
  }

  get idBatiments(): number[] {
    return this._idBatiments;
  }

  set idBatiments(value: number[]) {
    this._idBatiments = value;
  }
}
