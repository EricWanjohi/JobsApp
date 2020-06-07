package ke.co.droidsense.jobs.Models;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "STKPush")
public class STKPush {
    //Member Variables...
    @SerializedName("BusinessShortCode")
    @Expose
    private String businessShortCode;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Timestamp")
    @Expose
    private String timestamp;
    @SerializedName("TransactionType")
    @Expose
    private String transactionType;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("PartyA")
    @Expose
    private String partyA;
    @SerializedName("PartyB")
    @Expose
    private String partyB;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("CallBackURL")
    @Expose
    private String callBackURL;
    @SerializedName("AccountReference")
    @Expose
    private String accountReference;
    @SerializedName("TransactionDesc")
    @Expose
    private String transactionDesc;

    /**
     * No args constructor for use in serialization
     */
    public STKPush() {
    }

    //Constructor...

    /**
     * @param transactionType
     * @param partyA
     * @param password
     * @param amount
     * @param phoneNumber
     * @param callBackURL
     * @param accountReference
     * @param partyB
     * @param businessShortCode
     * @param timestamp
     * @param transactionDesc
     */
    public STKPush(String businessShortCode, String password, String timestamp, String transactionType, String amount, String partyA, String partyB, String phoneNumber, String callBackURL, String accountReference, String transactionDesc) {
        super();
        this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.phoneNumber = phoneNumber;
        this.callBackURL = callBackURL;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
    }

    //Getters and Setters...

    public String getBusinessShortCode() {
        return businessShortCode;
    }

    public void setBusinessShortCode(String businessShortCode) {
        this.businessShortCode = businessShortCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallBackURL() {
        return callBackURL;
    }

    public void setCallBackURL(String callBackURL) {
        this.callBackURL = callBackURL;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    /**
     * {
     *   "BusinessShortCode": "174379",
     *   "Password":"MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMjAwNjA3MTcxNTMw",
     *   "Timestamp": "20200607171530",
     *   "TransactionType": "CustomerPayBillOnline",
     *   "Amount": "1",
     *   "PartyA": "254715333331",
     *   "PartyB": "174379",
     *   "PhoneNumber": "254715333331",
     *   "CallBackURL": "https://mpesa-requestbin.herokuapp.com/1036qt91",
     *   "AccountReference": "Test",
     *   "TransactionDesc": "Testing Request"
     *  }
     */

}