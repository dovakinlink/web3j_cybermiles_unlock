import link.dovakin.Mytoken_sol_MyAdvancedToken;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class UnlockAndTransfer {

    // Cybermiles Token 的合约账户地址
    private static final String _CYBERMILES_CONTRACT_ADDRESS="0x0437fdf399bef52fef7bdf460918ac50b2f427be";

    // 需要转入的地址
    private static final String _MY_WALLET="0x229141af360cdefd6c790c6adfe4dd5de4ab5846";

    // 待连入的可正常工作的节点客户端地址
    private static final String _CLIENT_ADDRESS="http://localhost:8545";

    // 合约token精度
    private static final long _CMT_DECIMALS= 18;

    // 每个账户预留的token数量
    private static final long _CMT_BALANCE=1000;

    // 第一期放出的私钥前半部分
    private static final List<String> headPrivateKeyList = HeadKeys.keys;

    // 第二期放出的私钥后半部分
    private static final List<String> tailPrivateKeyList = TailKeys.keys;

    public static void main(String args[]){

        Web3j web3j = Web3j.build(new HttpService(_CLIENT_ADDRESS));
        BigInteger gasPrice = null;
        // 同步方式获取当前GasPrice
        try {
            gasPrice = web3j.ethGasPrice().send().getGasPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 计算出正确精度下的转账金额
        BigInteger balance =
                (BigInteger.valueOf((long) Math.pow(10, _CMT_DECIMALS)))
                        .multiply(BigInteger.valueOf(_CMT_BALANCE));

        int count = 0;
        // 穷举找出正确私钥并转出Token
        for (String headKey : headPrivateKeyList){
            for (String tailKey : tailPrivateKeyList){
                Credentials credentials = Credentials.create(headKey + tailKey);
                Mytoken_sol_MyAdvancedToken token = Mytoken_sol_MyAdvancedToken.load(
                        _CYBERMILES_CONTRACT_ADDRESS, web3j, credentials,
                        gasPrice, BigInteger.valueOf(250000)
                );
                try {
                    TransactionReceipt receipt
                            = token.transfer(_MY_WALLET, balance).send();
                    if (receipt.isStatusOK()){
                        System.out.print("成功解锁第" + ++count + "个账户,并转出1000个token");
                        tailPrivateKeyList.remove(tailKey);
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
