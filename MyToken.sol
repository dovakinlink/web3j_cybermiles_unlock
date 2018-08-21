pragma solidity ^0.4.18;
//基类
contract owned{
  // 合约拥有者
  address public owner;
  // 构造函数
  constructor() public {
    owner = msg.sender;
  }
  // 函数修饰符，限制只有合约拥有者才能进行操作
  modifier onlyOwner{
      require(msg.sender == owner);
      _;
  }
  // 禅让拥有者身份
  function transferOwnership(address newOwner) onlyOwner public {
    owner = newOwner;
  }
}

interface tokenRecipient {
    function receiveApproval(address _from, uint256 _value, address _token, bytes _extraData) external ;

}

contract TokenERC20 {
  // 代币名称
  string public name;
  // 代币符号
  string public symbol;
  // 18位的小数精度
  uint8 public decimals = 18;
  // 代币总量
  uint256 public totalSupply;

  // 持久化在区块链上的代币哈希存储映射结构，用来记录各账户代币持有量
  mapping( address => uint256 ) public balanceOf;
  // 预付许可
  mapping( address => mapping( address => uint256 ) ) public allowance;

  // 生成公有事件供区块链客户端监听
  event Transfer(address indexed from, address indexed to, uint256 value);

  // This notifies clients about the amount burnt
  event Burn(address indexed from, uint256 value);

  /**
    * Construct function
    * Initializes contract with initial supply tokens to the creator of the contract
  **/
  // 构造函数
  constructor(
    uint256 initialSupply,
    string tokenName,
    string tokenSymbol
    ) public {
      // 初始化代币最小精度数量
      totalSupply = initialSupply * 10 ** uint256 (decimals);
      // 默认将初始化所有代币归为合约创建者所有
      balanceOf[msg.sender] = totalSupply;
      // 设置代币名称
      name = tokenName;
      // 设置代币符号（标识符）
      symbol = tokenSymbol;
    }

    /**
     * 内部方法，只能被本合约自己调用
    **/
    function _transfer(address _from, address _to, uint _value) internal {
       // 发送目标地址不能使空
       require( _to != 0x0);
       // 检测发送账户是否有足够的代币完成转账
       require(balanceOf[_from] >= _value);
       // 检测溢出
       require(balanceOf[_to] + _value > balanceOf[_to]);
       // 记录发送接收账户总代币量
       uint previousBalances = balanceOf[_from] + balanceOf[_to];

       balanceOf[_from] -= _value;
       balanceOf[_to] += _value;

       emit Transfer(_from, _to, _value);
       assert(balanceOf[_from] + balanceOf[_to] == previousBalances);

    }

    /**
     * 交易Token
     * 从本账户发送 '_value' 的token到 '_to' 账户
     * @param _to 接受账户
     * @param _value 发送数量
    **/
    function transfer(address _to , uint256 _value) public{
      _transfer(msg.sender, _to, _value);
    }

    /**
     * 两个账户之间转移代币
     *
     * 发送 '_value' token 到 '_to' 从 '_from' 扣除
     *
     * @param _from 发送者地址
     * @param _to The 接收者地址
     * @param _value 发送数量
    **/
    function transferFrom(address _from, address _to, uint256 _value) public returns (bool success) {
      // Check allowance
      require( _value <= allowance[_from][msg.sender]);
      allowance[_from][msg.sender] -= _value;
      _transfer(_from, _to, _value);
      return true;
    }

    function approve(address _spender, uint256 _value) public returns (bool success){
        allowance[msg.sender][_spender] = _value;
        return true;
    }

    function approveAndCall(address _spender, uint256 _value, bytes _extraData) public returns (bool success){
      tokenRecipient spender = tokenRecipient(_spender);
      if (approve(_spender, _value)){
        spender.receiveApproval(msg.sender, _value, this, _extraData);
        return true;
      }
    }

    function burn(uint256 _value) public returns (bool success) {
      // Check if the sender has enough
      require(balanceOf[msg.sender] >= _value);
      // Substract from the sender
      balanceOf[msg.sender] -= _value;
      // Updates totalSupply
      totalSupply -= _value;
      emit Burn(msg.sender, _value);
      return true;
    }

    function burnFrom(address _from, uint256 _value) public returns (bool success){
      // Check if the targeted balance is enough
      require(balanceOf[_from] >= _value);
      // Check allowance
      require(_value <= allowance[_from][msg.sender]);
      // Subtract from the targeted balance
      balanceOf[_from] -= _value;
      // Subtract from the sender's allowance
      allowance[_from][msg.sender] -= _value;
      // Update totalSupply
      totalSupply -= _value;
      emit Burn(_from, _value);
      return true;
    }

}


contract MyToken is owned, TokenERC20 {
  uint256 public sellPrice;
  uint256 public buyPrice;

  mapping(address => bool) public frozenAccount;

  event FrozenFuns(address target, bool frozen);

  constructor(
    uint256 initialSupply,
    string tokenName,
    string tokenSymbol
    ) TokenERC20(initialSupply, tokenName, tokenSymbol) public{}

  function _transfer(address _from, address _to, uint _value) internal {
    require(_to != 0x0);
    require(balanceOf[_from] >= _value);
    require(balanceOf[_to] + _value > balanceOf[_to]);
    require(!frozenAccount[_from]);
    require(!frozenAccount[_to]);
    balanceOf[_from] -= _value;
    balanceOf[_to] += _value;
    emit Transfer(_from, _to, _value);
  }

  function mintToken(address target, uint256 mintedAmount) onlyOwner public{
    balanceOf[target] += mintedAmount;
    totalSupply += mintedAmount;
    emit Transfer(0, this, mintedAmount);
    emit Transfer(this, target, mintedAmount);
  }

  function freezeAccount(address target, bool freeze) onlyOwner public{
    frozenAccount[target] = freeze;
    emit FrozenFuns(target, freeze);
  }

  function setPrices(uint256 newSellPrice, uint256 newBuyPrice) onlyOwner public{
      sellPrice = newSellPrice;
      buyPrice = newBuyPrice;
  }

  function buy() payable public{
    uint amount = msg.value / buyPrice;
    _transfer(this, msg.sender, amount);
  }

  function sell(uint256 amount) public {
    require(address(this).balance >= amount * sellPrice);
    _transfer(msg.sender, this, amount);
    msg.sender.transfer(amount * sellPrice);
  }

}
