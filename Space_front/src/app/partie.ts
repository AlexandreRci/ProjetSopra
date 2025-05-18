export class Partie {
    constructor(
        private _id: number,
        private _currentPosition: number,
        private _nbJoueur: number,
        private _nbTour: number,
        private _statut: string
    ) {}

    get id(): number{
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get currentPosition(): number {
        return this._currentPosition;
    }

    set currentPosition(value: number) {
        this._currentPosition = value;
    }

    get nbJoueur(): number {
        return this._nbJoueur;
    }

    set nbJoueur(value: number) {
        this._nbJoueur = value;
    }    

    get nbTour(): number {
        return this._nbTour;
    }

    set nbTour(value: number) {
        this._nbTour = value;
    }

    get statut(): string {
        return this._statut;
    }

    set statut(value: string) {
        this._statut = value;
    }

}

