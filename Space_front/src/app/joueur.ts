export class Joueur {
  constructor(
    private _id: number,
    private _position: number,
    private _idPartie: number,
    private _idEspece: number,
    private _idPossessions: number[],
    private _idPlanetSeeds: number[]
  ) {}

  get id(): number {
    return this._id;
  }
  set id(value: number) {
    this._id = value;
  }

  get position(): number {
    return this._position;
  }
  set position(value: number) {
    this._position = value;
  }

  get idPartie(): number {
    return this._idPartie;
  }
  set idPartie(value: number) {
    this._idPartie = value;
  }

  get idEspece(): number {
    return this._idEspece;
  }
  set idEspece(value: number) {
    this._idEspece = value;
  }

  get idPossessions(): number[] {
    return this._idPossessions;
  }
  set idPossessions(value: number[]) {
    this._idPossessions = value;
  }

  get idPlanetSeeds(): number[] {
    return this._idPlanetSeeds;
  }
  set idPlanetSeeds(value: number[]) {
    this._idPlanetSeeds = value;
  }
}
