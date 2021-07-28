#!/bin/bash 
# To run the script "bash scriptName.sh"
# Or make it executable chmod +x scriptName.sh then ./scriptName.sh

# Remove objects(files) from S3 which contains some specific substring in the object name.
listObjects=$(aws s3api list-objects --bucket sbilung --no-paginate --query Contents[].Key)

deleteObject () {
  delete=$(aws s3api delete-object --bucket sbilung --key $1)
  echo "Deletion : "$1" "$delete  
}

for object in $listObjects
do
  if [[ $object =~ .*"GeoIP2-Country-Locations-en.csv".* || $object =~ .*"maxmind-update.txt".* ]]; then
    deleteObject "$object"
  fi    
done 

echo "Done !!!"



