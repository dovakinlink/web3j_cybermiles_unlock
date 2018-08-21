package link.dovakin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Mytoken_sol_MyAdvancedToken extends Contract {
    private static final String BINARY = "60806040526003805460ff1916601217905534801561001d57600080fd5b50604051610db8380380610db883398101604090815281516020808401518385015160008054600160a060020a03191633908117825560035460ff16600a0a86026004819055908252600585529590209490945584018051929490930191849184918491610090916001918501906100b0565b5080516100a49060029060208401906100b0565b5050505050505061014b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100f157805160ff191683800117855561011e565b8280016001018555821561011e579182015b8281111561011e578251825591602001919060010190610103565b5061012a92915061012e565b5090565b61014891905b8082111561012a5760008155600101610134565b90565b610c5e8061015a6000396000f3006080604052600436106101275763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166305fefda7811461012c57806306fdde0314610149578063095ea7b3146101d357806318160ddd1461020b57806323b872dd14610232578063313ce5671461025c57806342966c68146102875780634b7503341461029f57806370a08231146102b457806379c65068146102d557806379cc6790146102f95780638620410b1461031d5780638da5cb5b1461033257806395d89b4114610363578063a6f2ae3a14610378578063a9059cbb14610380578063b414d4b6146103a4578063cae9ca51146103c5578063dd62ed3e1461042e578063e4849b3214610455578063e724529c1461046d578063f2fde38b14610493575b600080fd5b34801561013857600080fd5b506101476004356024356104b4565b005b34801561015557600080fd5b5061015e6104d6565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610198578181015183820152602001610180565b50505050905090810190601f1680156101c55780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101df57600080fd5b506101f7600160a060020a0360043516602435610563565b604080519115158252519081900360200190f35b34801561021757600080fd5b50610220610590565b60408051918252519081900360200190f35b34801561023e57600080fd5b506101f7600160a060020a0360043581169060243516604435610596565b34801561026857600080fd5b50610271610605565b6040805160ff9092168252519081900360200190f35b34801561029357600080fd5b506101f760043561060e565b3480156102ab57600080fd5b50610220610686565b3480156102c057600080fd5b50610220600160a060020a036004351661068c565b3480156102e157600080fd5b50610147600160a060020a036004351660243561069e565b34801561030557600080fd5b506101f7600160a060020a0360043516602435610754565b34801561032957600080fd5b50610220610825565b34801561033e57600080fd5b5061034761082b565b60408051600160a060020a039092168252519081900360200190f35b34801561036f57600080fd5b5061015e61083a565b610147610892565b34801561038c57600080fd5b50610147600160a060020a03600435166024356108b2565b3480156103b057600080fd5b506101f7600160a060020a03600435166108c1565b3480156103d157600080fd5b50604080516020600460443581810135601f81018490048402850184019095528484526101f7948235600160a060020a03169460248035953695946064949201919081908401838280828437509497506108d69650505050505050565b34801561043a57600080fd5b50610220600160a060020a03600435811690602435166109ef565b34801561046157600080fd5b50610147600435610a0c565b34801561047957600080fd5b50610147600160a060020a03600435166024351515610a59565b34801561049f57600080fd5b50610147600160a060020a0360043516610ad4565b600054600160a060020a031633146104cb57600080fd5b600791909155600855565b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561055b5780601f106105305761010080835404028352916020019161055b565b820191906000526020600020905b81548152906001019060200180831161053e57829003601f168201915b505050505081565b336000908152600660209081526040808320600160a060020a039590951683529390529190912055600190565b60045481565b600160a060020a03831660009081526006602090815260408083203384529091528120548211156105c657600080fd5b600160a060020a03841660009081526006602090815260408083203384529091529020805483900390556105fb848484610b1a565b5060019392505050565b60035460ff1681565b3360009081526005602052604081205482111561062a57600080fd5b3360008181526005602090815260409182902080548690039055600480548690039055815185815291517fcc16f5dbb4873280815c1ee09dbd06736cffcc184412cf7a71a0fdb75d397ca59281900390910190a2506001919050565b60075481565b60056020526000908152604090205481565b600054600160a060020a031633146106b557600080fd5b600160a060020a03821660009081526005602090815260408083208054850190556004805485019055805184815290513093927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef928290030190a3604080518281529051600160a060020a0384169130917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9181900360200190a35050565b600160a060020a03821660009081526005602052604081205482111561077957600080fd5b600160a060020a03831660009081526006602090815260408083203384529091529020548211156107a957600080fd5b600160a060020a0383166000818152600560209081526040808320805487900390556006825280832033845282529182902080548690039055600480548690039055815185815291517fcc16f5dbb4873280815c1ee09dbd06736cffcc184412cf7a71a0fdb75d397ca59281900390910190a250600192915050565b60085481565b600054600160a060020a031681565b6002805460408051602060018416156101000260001901909316849004601f8101849004840282018401909252818152929183018282801561055b5780601f106105305761010080835404028352916020019161055b565b6000600854348115156108a157fe5b0490506108af303383610b1a565b50565b6108bd338383610b1a565b5050565b60096020526000908152604090205460ff1681565b6000836108e38185610563565b156109e7576040517f8f4ffcb10000000000000000000000000000000000000000000000000000000081523360048201818152602483018790523060448401819052608060648501908152875160848601528751600160a060020a03871695638f4ffcb195948b94938b939192909160a490910190602085019080838360005b8381101561097b578181015183820152602001610963565b50505050905090810190601f1680156109a85780820380516001836020036101000a031916815260200191505b5095505050505050600060405180830381600087803b1580156109ca57600080fd5b505af11580156109de573d6000803e3d6000fd5b50505050600191505b509392505050565b600660209081526000928352604080842090915290825290205481565b600754810230311015610a1e57600080fd5b610a29333083610b1a565b6007546040513391830280156108fc02916000818181858888f193505050501580156108bd573d6000803e3d6000fd5b600054600160a060020a03163314610a7057600080fd5b600160a060020a038216600081815260096020908152604091829020805460ff191685151590811790915582519384529083015280517f3ff3f3c87127ddb6ec1bfc446631fc78dceed74b564fb53522ac6f11f12165439281900390910190a15050565b600054600160a060020a03163314610aeb57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600160a060020a0382161515610b2f57600080fd5b600160a060020a038316600090815260056020526040902054811115610b5457600080fd5b600160a060020a03821660009081526005602052604090205481810111610b7a57600080fd5b600160a060020a03831660009081526009602052604090205460ff1615610ba057600080fd5b600160a060020a03821660009081526009602052604090205460ff1615610bc657600080fd5b600160a060020a03808416600081815260056020908152604080832080548790039055938616808352918490208054860190558351858152935191937fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929081900390910190a35050505600a165627a7a7230582014e0c24115b0fef333e000e93af46a8c89bca701db38921b542ce04e9cf9e4cb0029";

    public static final String FUNC_SETPRICES = "setPrices";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_SELLPRICE = "sellPrice";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_MINTTOKEN = "mintToken";

    public static final String FUNC_BURNFROM = "burnFrom";

    public static final String FUNC_BUYPRICE = "buyPrice";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_FROZENACCOUNT = "frozenAccount";

    public static final String FUNC_APPROVEANDCALL = "approveAndCall";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_SELL = "sell";

    public static final String FUNC_FREEZEACCOUNT = "freezeAccount";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event FROZENFUNS_EVENT = new Event("FrozenFuns", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event BURN_EVENT = new Event("Burn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected Mytoken_sol_MyAdvancedToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Mytoken_sol_MyAdvancedToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> setPrices(BigInteger newSellPrice, BigInteger newBuyPrice) {
        final Function function = new Function(
                FUNC_SETPRICES, 
                Arrays.<Type>asList(new Uint256(newSellPrice),
                new Uint256(newBuyPrice)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> burn(BigInteger _value) {
        final Function function = new Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> sellPrice() {
        final Function function = new Function(FUNC_SELLPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> balanceOf(String param0) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> mintToken(String target, BigInteger mintedAmount) {
        final Function function = new Function(
                FUNC_MINTTOKEN, 
                Arrays.<Type>asList(new Address(target),
                new Uint256(mintedAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> burnFrom(String _from, BigInteger _value) {
        final Function function = new Function(
                FUNC_BURNFROM, 
                Arrays.<Type>asList(new Address(_from),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> buyPrice() {
        final Function function = new Function(FUNC_BUYPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> buy(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> frozenAccount(String param0) {
        final Function function = new Function(FUNC_FROZENACCOUNT, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> approveAndCall(String _spender, BigInteger _value, byte[] _extraData) {
        final Function function = new Function(
                FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_value),
                new org.web3j.abi.datatypes.DynamicBytes(_extraData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String param0, String param1) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new Address(param0),
                new Address(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> sell(BigInteger amount) {
        final Function function = new Function(
                FUNC_SELL, 
                Arrays.<Type>asList(new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> freezeAccount(String target, Boolean freeze) {
        final Function function = new Function(
                FUNC_FREEZEACCOUNT, 
                Arrays.<Type>asList(new Address(target),
                new Bool(freeze)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Mytoken_sol_MyAdvancedToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String tokenName, String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(initialSupply),
                new Utf8String(tokenName),
                new Utf8String(tokenSymbol)));
        return deployRemoteCall(Mytoken_sol_MyAdvancedToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Mytoken_sol_MyAdvancedToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String tokenName, String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(initialSupply),
                new Utf8String(tokenName),
                new Utf8String(tokenSymbol)));
        return deployRemoteCall(Mytoken_sol_MyAdvancedToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<FrozenFunsEventResponse> getFrozenFunsEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(FROZENFUNS_EVENT, transactionReceipt);
        ArrayList<FrozenFunsEventResponse> responses = new ArrayList<FrozenFunsEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FrozenFunsEventResponse typedResponse = new FrozenFunsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.frozen = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FrozenFunsEventResponse> frozenFunsEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, FrozenFunsEventResponse>() {
            @Override
            public FrozenFunsEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FROZENFUNS_EVENT, log);
                FrozenFunsEventResponse typedResponse = new FrozenFunsEventResponse();
                typedResponse.log = log;
                typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.frozen = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<FrozenFunsEventResponse> frozenFunsEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FROZENFUNS_EVENT));
        return frozenFunsEventObservable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventObservable(filter);
    }

    public List<BurnEventResponse> getBurnEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(BURN_EVENT, transactionReceipt);
        ArrayList<BurnEventResponse> responses = new ArrayList<BurnEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BurnEventResponse typedResponse = new BurnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BurnEventResponse> burnEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, BurnEventResponse>() {
            @Override
            public BurnEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BURN_EVENT, log);
                BurnEventResponse typedResponse = new BurnEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<BurnEventResponse> burnEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURN_EVENT));
        return burnEventObservable(filter);
    }

    public static Mytoken_sol_MyAdvancedToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mytoken_sol_MyAdvancedToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Mytoken_sol_MyAdvancedToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mytoken_sol_MyAdvancedToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class FrozenFunsEventResponse {
        public Log log;

        public String target;

        public Boolean frozen;
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger value;
    }

    public static class BurnEventResponse {
        public Log log;

        public String from;

        public BigInteger value;
    }
}
