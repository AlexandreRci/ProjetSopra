export class Joueur {
    constructor(
        private _id: number,
        private _position: number,
        private _espece_id: number,
        private _partie_id: number,
        private _utilisateur_id: number,


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

    get espece_id(): number {
        return this._espece_id;
    }

    set espece_id(value: number) {
        this._espece_id = value;
    }

    get partie_id(): number {
        return this._partie_id;
    }

    set partie_id(value: number) {
        this._partie_id = value;
    }

    get utilisateur_id(): number {
        return this._utilisateur_id;
    }

    set utilisateur_id(value: number) {
        this._utilisateur_id = value;
    }






}
