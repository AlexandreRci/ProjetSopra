export class CompteResponse {
    constructor(
      public id: number,
      public username: string,
      public token: string
    ) {}
  
    public getToken(): string {
      return this.token;
    }

    public getId(): number {
      return this.id;
    }

  }
  