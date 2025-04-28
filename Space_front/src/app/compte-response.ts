export class CompteResponse {
    constructor(private success: boolean, private token: string) { }

    public getSuccess(): boolean {
        return this.success;
    }

    public setSuccess(value: boolean) {
        this.success = value;
    }

    public getToken(): string {
        return this.token;
    }

    public setToken(value: string) {
        this.token = value;
    }
}
