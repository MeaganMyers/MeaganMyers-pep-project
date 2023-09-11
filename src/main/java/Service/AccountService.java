package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    //no-args constructor for creating a new AccountService witha new AccountDAO
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    //Constructor for a AccountService when a AccountDAO is provided
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    //User registration
    //create a new account
    //username cannot be blank, password has to be at least 4 characters, and username cannot already exist
    public Account addAccount(Account account) {
        account.setAccount_id(0);
        if(!account.getUsername().isEmpty() && account.getPassword().length() >= 4 && account.getUsername() == account.getUsername()) {
            return accountDAO.insertAccount(account);
        }
        return null;
    } //end registration

    //login
    //verify account login
    //if username and password match an existing account return the account including account_id
    public Account checkAccount(String username, String password, Account account) {
        if(username.equals(account.getUsername()) && password.equals(account.getPassword())) {
            return accountDAO.verifyAccount(username, password, account);
        }
        return null;
    } //end login
}
