export class StartRequest {
        constructor(
        public userId: number,
        public idEspece: number,
    ) { }

    public getIdEspece(): number {
        return this.idEspece;
    }
    public getUserId(): number {
        return this.userId;
    }
    public setIdEspece(idEspece: number): void {
        this.idEspece = idEspece;
    }
    public setUserId(userId: number): void {
        this.userId = userId;
    }
}
