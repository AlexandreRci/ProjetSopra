export class Compte {
  constructor(
    public id: number | null = null,
    public name: string = '',
    public username: string = '',
    public password: string = '',
    public compteType: string = 'UTILISATEUR'
  ) {}

  public getId(): number | null {
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
