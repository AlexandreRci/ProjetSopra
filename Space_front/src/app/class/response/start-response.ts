export class StartResponse {
    constructor(
        public idGame: number,
        public idPlayer: number,
    ) { }

    public getIdGame(): number {
        return this.idGame;
    }
    public getIdPlayer(): number {
        return this.idPlayer;
    }
    public setIdGame(idGame: number): void {
        this.idGame = idGame;
    }
    public setIdPlayer(idPlayer: number): void {
        this.idPlayer = idPlayer;
    }
}
