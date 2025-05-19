export class Possession {
  constructor(
    private _id: number,
    private _quantite: number,
    private _ressource: string
  ) {}

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get quantite(): number {
    return this._quantite;
  }

  set quantite(value: number) {
    this._quantite = value;
  }

  get ressource(): string {
    return this._ressource;
  }

  set ressource(value: string) {
    this._ressource = value;
  }
}
