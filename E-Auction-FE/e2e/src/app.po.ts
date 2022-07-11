import { browser, by, element, ElementFinder } from 'protractor';

export class AppPage {
  navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl) as Promise<unknown>;
  }

  getTitleText(): Promise<string> {
    return element(by.css('app-root .content span')).getText() as Promise<string>;
  }

  toHeader(): ElementFinder {
    return element(by.tagName('<app-header>'));
  }

  toFooter(): ElementFinder {
    return element(by.tagName('<app-footer>'));
  }
  
  navigateToBase() {
    return browser.get('/');
  }
}
