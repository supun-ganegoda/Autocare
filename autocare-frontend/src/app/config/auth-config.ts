import { PassedInitialConfig } from 'angular-auth-oidc-client';

/*
here need to create a client in keycloak called angular client, use security flow as standard flow and keep user
authenticated false, because this is a public user
Also enable user registration in realm settings
*/
export const authConfig: PassedInitialConfig = {
  config: {
    authority: 'http://localhost:8181/realms/Autocare-credentials',
    redirectUrl: window.location.origin,
    postLogoutRedirectUri: window.location.origin,
    clientId: 'angular-client',
    scope: 'openid profile offline_access',
    responseType: 'code',
    silentRenew: true,
    useRefreshToken: true,
    renewTimeBeforeTokenExpiresInSeconds: 30,
  }
}
