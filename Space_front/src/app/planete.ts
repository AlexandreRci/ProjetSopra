export class Planete {
    constructor(
        private _id: number,
        private _nom: string,
        private _minerai: number,
        private _biomes: string[]
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

    get minerai(): number {
        return this._minerai;
    }

    set minerai(value: number) {
        this._minerai = value;
    }

    get biomes(): string[] {
        return this._biomes;
    }

    set biomes(value: string[]) {
        this._biomes = value;
    }
}