import unittest
import time
import re
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class DobreZieleBasketTestChrome(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)

    def test_dobreziele_basket_check(self):
        driver = self.driver
        discount_code="newsletter-447d13f5"

        driver.get("https://dobreziele.pl/")
        self.assertIn("Yerba Mate", driver.title)

        element = driver.find_element(By.NAME, "k")
        element.send_keys("aguamate")
        element.send_keys(Keys.RETURN)

        wait = WebDriverWait(driver, 10)
        element = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, 'a[href*=\'aguamate-500g\']')))
        element.click()
        
        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'basket')))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'alert-infos')))
        wait.until(EC.invisibility_of_element_located(element))
        
        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'basket-box')))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'Aguamate 500g')]")))

        original_price = re.findall("\d+\,\d+", driver.find_element(By.CLASS_NAME, 'totalPrice').text)[0].replace(',','')

        element = wait.until(EC.element_to_be_clickable((By.NAME, "discount_code")))
        element.send_keys(discount_code)
        
        element = driver.find_element(By.XPATH, "//*[contains(text(), 'sprawdź rabat')]")
        element.click()

        time.sleep(1)
        discounted_price = re.findall("\d+\,\d+", driver.find_element(By.CLASS_NAME, 'totalPrice').text)[0].replace(',','')
        self.assertEqual(float(original_price)*0.95, float(discounted_price))

    def tearDown(self):
        self.driver.close()

class DobreZieleBasketTestFirefox(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(10)

    def test_dobreziele_basket_check(self):
        driver = self.driver
        discount_code="newsletter-447d13f5"
        driver.maximize_window()

        driver.get("https://dobreziele.pl/")
        self.assertIn("Yerba Mate", driver.title)

        element = driver.find_element(By.NAME, "k")
        element.send_keys("aguamate")
        element.send_keys(Keys.RETURN)

        wait = WebDriverWait(driver, 10)
        element = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, 'a[href*=\'aguamate-500g\']')))
        element.click()
        
        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'basket')))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'alert-infos')))
        wait.until(EC.invisibility_of_element_located(element))

        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'basket-box')))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'Aguamate 500g')]")))

        original_price = re.findall("\d+\,\d+", driver.find_element(By.CLASS_NAME, 'totalPrice').text)[0].replace(',','')

        element = wait.until(EC.element_to_be_clickable((By.NAME, "discount_code")))
        element.send_keys(discount_code)
        
        element = driver.find_element(By.XPATH, "//*[contains(text(), 'sprawdź rabat')]")
        element.click()

        time.sleep(1)
        discounted_price = re.findall("\d+\,\d+", driver.find_element(By.CLASS_NAME, 'totalPrice').text)[0].replace(',','')
        self.assertEqual(float(original_price)*0.95, float(discounted_price))

    def tearDown(self):
        self.driver.close()
if __name__ == "__main__":
    unittest.main()