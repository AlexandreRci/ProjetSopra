export class Batiment {
  constructor(
    private _id: number,
    private _nom: string,
    private _taille: string,
    private _ressource: string
  ) {}

  get id(): number {
    return this._id;
  }
  set id(value: number) {
    this._id = value;
  }

  get nom(): string {
    return this._nom;
  }
  set nom(value: string) {
    this._nom = value;
  }

  get taille(): string {
    return this._taille;
  }
  set taille(value: string) {
    this._taille = value;
  }

  get ressource(): string {
    return this._ressource;
  }
  set ressource(value: string) {
    this._ressource = value;
  }
}
