export class Espece {
  constructor(
    private _id: number,
    private _nom: string,
    private _biomes: { [key: string]: number }
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

  get biomes(): { [key: string]: number } {
    return this._biomes;
  }

  set biomes(value: { [key: string]: number }) {
    this._biomes = value;
  }
}
