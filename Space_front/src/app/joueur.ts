export class Joueur {
    constructor(
        private _id: number,
        private _position: number
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
}
