CREATE TABLE `admin` (
  `USERNAME` varchar(20) NOT NULL DEFAULT '',
  `EMAIL` varchar(30) DEFAULT NULL,
  `PHONE_NO` varchar(12) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bank_info` (
  `Supplier_Id` varchar(15) DEFAULT NULL,
  `Supplier_Name` varchar(30) DEFAULT NULL,
  `Bank_Name` varchar(50) DEFAULT NULL,
  `Bank_Account` varchar(20) DEFAULT NULL,
  `IFSC` varchar(15) DEFAULT NULL,
  KEY `bank_info_ibfk_1` (`Supplier_Id`),
  CONSTRAINT `bank_info_ibfk_1` FOREIGN KEY (`Supplier_Id`) REFERENCES `supplier_info` (`Supplier_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `company_info` (
  `COMPID` varchar(6) NOT NULL DEFAULT '',
  `COMPNAME` varchar(50) DEFAULT NULL,
  `GSTNO` varchar(15) DEFAULT NULL,
  `PANNO` varchar(10) DEFAULT NULL,
  `AADHAARNO` varchar(12) DEFAULT NULL,
  `OWNER1` varchar(50) DEFAULT NULL,
  `OWNER2` varchar(50) DEFAULT NULL,
  `ADDRESS1` varchar(150) DEFAULT NULL,
  `ADDRESS2` varchar(150) DEFAULT NULL,
  `PHONENO` varchar(15) DEFAULT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `DATE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`COMPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `confirm_order` (
  `Order_ID` varchar(20) DEFAULT NULL,
  `Barcode` varchar(100) DEFAULT NULL,
  `Quantity` varchar(10) DEFAULT NULL,
  `Item_Name` varchar(1000) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `GST` varchar(11) DEFAULT NULL,
  `Price_GST` float DEFAULT NULL,
  `Total_Price` float DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer_info` (
  `CustomerName` varchar(20) DEFAULT NULL,
  `CustomerPhone` varchar(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `CustomerEmail` varchar(100) DEFAULT NULL,
  `CustomerGST` varchar(20) DEFAULT NULL,
  `CustDate` varchar(20) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CustomerPhone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `gst_slab` (
  `Cat_ID` varchar(20) NOT NULL,
  `Cat_Name` varchar(20) NOT NULL DEFAULT '',
  `Gst_No` varchar(20) NOT NULL,
  `Status` varchar(20) DEFAULT 'Y',
  PRIMARY KEY (`Cat_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `in_register` (
  `Supplier_Id` varchar(20) DEFAULT NULL,
  `Cat_Id` varchar(20) DEFAULT NULL,
  `Item_Id` varchar(20) DEFAULT NULL,
  `Qty_In` varchar(20) DEFAULT NULL,
  `Price` varchar(11) DEFAULT NULL,
  `Dates` varchar(20) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `item_stock` (
  `CategoryId` varchar(20) DEFAULT NULL,
  `ItemId` varchar(20) DEFAULT NULL,
  `StartDate` varchar(20) DEFAULT NULL,
  `UnitID` varchar(20) DEFAULT NULL,
  `UnitSizeId` varchar(20) DEFAULT NULL,
  `OpenQty` varchar(20) DEFAULT NULL,
  `CurrentQty` varchar(20) DEFAULT NULL,
  `ReorderQty` varchar(20) DEFAULT NULL,
  `PricePerUnit` varchar(20) DEFAULT NULL,
  `EffectivePrice` varchar(15) DEFAULT NULL,
  `Status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `itemcategory` (
  `ItemName` varchar(30) NOT NULL,
  `ItemId` varchar(15) DEFAULT NULL,
  `cat_id` varchar(20) DEFAULT NULL,
  `Category_Name` varchar(30) DEFAULT NULL,
  `GST_No` varchar(20) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `Status` varchar(5) DEFAULT 'Y'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `offers` (
  `offer_id` varchar(20) NOT NULL DEFAULT '',
  `Offer` varchar(100) DEFAULT NULL,
  `Date` varchar(20) DEFAULT NULL,
  `Status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `openingaccount` (
  `Company_Id` varchar(50) NOT NULL DEFAULT '',
  `Company_Name` varchar(50) NOT NULL DEFAULT '',
  `Email_ID` varchar(50) DEFAULT NULL,
  `Opening_Rate` varchar(50) DEFAULT NULL,
  `Dates` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT 'Y',
  PRIMARY KEY (`Company_Id`,`Company_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_info` (
  `Order_ID` varchar(20) NOT NULL DEFAULT '',
  `Customer_Phone` varchar(20) DEFAULT NULL,
  `Customer_Name` varchar(200) DEFAULT NULL,
  `Dates` varchar(20) DEFAULT NULL,
  `Totalprice` float DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Order_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pending_order` (
  `Order_ID` varchar(20) DEFAULT NULL,
  `Barcode` varchar(100) DEFAULT NULL,
  `Quantity` varchar(10) DEFAULT NULL,
  `Item_Name` varchar(1000) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `GST` varchar(11) DEFAULT NULL,
  `Price_GST` float DEFAULT NULL,
  `Total_Price` float DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDBCREATE TABLE `supplier_info` (
  `Supplier_Id` varchar(20) NOT NULL DEFAULT '',
  `Supplier_Name` varchar(30) DEFAULT NULL,
  `Supplier_GST` varchar(20) DEFAULT NULL,
  `PAN_No` varchar(20) DEFAULT NULL,
  `Address` varchar(150) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Phone_No` varchar(15) DEFAULT NULL,
  `Email_ID` varchar(30) DEFAULT NULL,
  `cat_id` varchar(20) DEFAULT NULL,
  `BankAccount` varchar(20) DEFAULT NULL,
  `Dates` date DEFAULT NULL,
  `Status` varchar(5) DEFAULT 'Y',
  PRIMARY KEY (`Supplier_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; DEFAULT CHARSET=utf8;

CREATE TABLE `temp_order_info` (
  `Order_ID` varchar(20) DEFAULT NULL,
  `Barcode` varchar(100) DEFAULT NULL,
  `Quantity` int(10) DEFAULT NULL,
  `Item_Name` varchar(1000) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `GST` varchar(11) DEFAULT NULL,
  `Price_GST` float DEFAULT NULL,
  `Total_Price` float DEFAULT NULL,
  `Stock` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `unit_master` (
  `UnitID` varchar(20) NOT NULL DEFAULT '',
  `UnitName` varchar(30) DEFAULT NULL,
  `Discription` varchar(30) DEFAULT NULL,
  `Status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`UnitID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `unit_size_master` (
  `UnitID` varchar(20) DEFAULT NULL,
  `UnitSizeId` varchar(20) NOT NULL DEFAULT '',
  `SizeName` varchar(20) DEFAULT NULL,
  `Discription` varchar(30) DEFAULT NULL,
  `Status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`UnitSizeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `USERNAME` varchar(20) NOT NULL DEFAULT '',
  `EMAIL` varchar(30) DEFAULT NULL,
  `PHONE_NO` varchar(20) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;