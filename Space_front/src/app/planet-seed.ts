import { Planete } from "./planete";

export class PlanetSeed {

    constructor(
    private _id: number,
    private _population: number,
    private _arme: number,
    private _mineraiRestant: number,
    private _joueur: any, 
    private _planete: Planete,
    private _batiments: any[] 
  ) {}

    get id(): number {
        return this._id;
    }

    set id(value: number) {
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


    get joueur(): any {
        return this._joueur;
    }

    set joueur(value: any) {
        this._joueur = value;
    }


    get planete(): Planete {
        return this._planete;
    }

    set planete(value: Planete) {
        this._planete = value;
    }


    get batiments(): any[] {
        return this._batiments;
    }

    set batiments(value: any[]) {
        this._batiments = value;
    }

}
