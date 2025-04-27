export class Compte {
    constructor(private id: number|null, private name: string, private username: string, private password: string, private compteType: string) { }

    public getId(): number|null {
        return this.id;
    }

    public setId(value: number) {
        this.id = value;
    }

    public getName(): string {
        return this.name;
    }

    public setName(name: string) {
        this.name = name;
    }

    public getUsername(): string {
        return this.username;
    }

    public setUsername(username: string) {
        this.username = username;
    }

    public getPassword(): string {
        return this.password;
    }

    public setPassword(password: string) {
        this.password = password;
    }

    public getCompteType(): string {
        return this.compteType;
    }

    public setCompteType(compteType: string) {
        this.compteType = compteType;
    }
}
