import { AppPage } from './app.po';
import { browser, logging, element, By } from 'protractor';

describe('E-AUCTION APP', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should have <app-header>', () => {
    // Write logic here
    expect(page.toHeader).toBeTruthy();
  });

  it('should have <app-footer>', () => {
    // Write logic here
    expect(page.toFooter).toBeTruthy();
  });

  it('should load the home page', () => {
    // Write logic here
    page.navigateToBase();
    expect(browser.getCurrentUrl()).toEqual(browser.baseUrl);
  });

  it('should have <nav> with classes nav navbar-dark', async () => {
    // Write logic here
    page.navigateToBase();
    expect(element(By.tagName('nav')).getAttribute('class')).toEqual(
      'navbar navbar-dark bg-dark navbar-expand-md fixed-top'
    );
  });

  it('should have nav-brand of image', () => {
    const img = element(By.className('navbar-brand'));
    expect(img.element(By.tagName('img')).getAttribute('src')).toEqual(
      browser.baseUrl + 'assets/images/logo.webp'
    );
  });

  it('should have fetch details button', () => {
    const fetchDetailsButton = element(By.partialButtonText('Fetch Details'));
    expect(fetchDetailsButton).toBeTruthy();
    fetchDetailsButton.click();
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toEqual(browser.baseUrl);
    expect(element(By.partialButtonText('Product'))).toBeTruthy();
    expect(element(By.partialButtonText('Get'))).toBeTruthy();
    expect(element(By.xpath('/html/body/app-root/app-home/div/div[2]/div[1]/div[1]/div[2]/select'))).toBeTruthy();
  });

  it('should have fetch details button and verify all the elements', () => {
    const fetchDetailsButton = element(By.partialButtonText("Fetch Details"));
    expect(fetchDetailsButton).toBeTruthy();
    fetchDetailsButton.click();
    browser.sleep(2000);  
    const productDropdown = element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[1]/div[2]/select"));
    expect(productDropdown.getText).not.toBeNull();
    element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[1]/div[2]/select/option[4]")).click();
    browser.sleep(2000);
    element(By.partialButtonText("Get")).click();
    browser.sleep(2000);
    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[1]/div[1]/label")).getText()).toEqual("Product Name");
    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[1]/div[2]/label")).getText()).toEqual("Short Decription");
    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[1]/div[3]/label")).getText()).toEqual("Detailed Decription");
    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[1]/div[4]/label")).getText()).toEqual("Category");
    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[1]/div[5]/label")).getText()).toEqual("Starting Price");
    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[1]/div[6]/label")).getText()).toEqual("Bid End Date");

    expect(element(By.xpath("//*[@id='productName']")).getText()).not.toBeNull();
    expect(element(By.xpath("//*[@id='shortDesc']")).getText()).not.toBeNull();
    expect(element(By.xpath("//*[@id='detailedDesc']")).getText()).not.toBeNull();
    expect(element(By.xpath("//*[@id='category']")).getText()).not.toBeNull();
    expect(element(By.xpath("//*[@id='startingPrice']")).getText()).not.toBeNull();
    expect(element(By.xpath("//*[@id='EndDate']")).getText()).not.toBeNull();

    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[2]/table"))).toBeTruthy();
    expect(element(By.xpath("/html/body/app-root/app-home/div/div[2]/div[1]/div[2]/div[2]/table/tbody/tr[1]/th"))).not.toBeNull();
  });

});
